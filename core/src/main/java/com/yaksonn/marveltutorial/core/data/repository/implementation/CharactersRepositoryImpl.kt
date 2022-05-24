package com.yaksonn.marveltutorial.core.data.repository.implementation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yaksonn.marveltutorial.core.cache.abstraction.CharactersCache
import com.yaksonn.marveltutorial.core.cache.mapper.CharacterEntityMapper
import com.yaksonn.marveltutorial.core.data.repository.abstraction.CharactersRepository
import com.yaksonn.marveltutorial.core.domain.executor.abstraction.PostExecutionThread
import com.yaksonn.marveltutorial.core.domain.model.characters.Character
import com.yaksonn.marveltutorial.core.domain.model.comics.Comic
import com.yaksonn.marveltutorial.core.remote.abstraction.CharactersRemote
import com.yaksonn.marveltutorial.core.remote.mapper.characters.CharacterMapper
import com.yaksonn.marveltutorial.core.remote.mapper.comics.ComicModelMapper
import com.yaksonn.marveltutorial.core.remote.pagination.CharactersPagingSource
import com.yaksonn.marveltutorial.core.remote.util.Resource
import com.yaksonn.marveltutorial.core.util.Constants
import com.yaksonn.marveltutorial.core.util.Constants.LIMIT
import com.yaksonn.marveltutorial.core.util.extensions.toMD5
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterMapper: CharacterMapper,
    private val charactersRemote: CharactersRemote,
    private val comicModelMapper: ComicModelMapper,
    private val postExecutionThread: PostExecutionThread,
    private val characterEntityMapper: CharacterEntityMapper,
    private val charactersCache: CharactersCache
) : CharactersRepository {

    private val ts = System.currentTimeMillis().toString()

    private val hash = Constants.HASH_FORMAT.format(
        ts,
        Constants.PRIVATE_API_KEY,
        Constants.PUBLIC_API_KEY
    ).toMD5()

    override fun fetchCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = LIMIT, enablePlaceholders = false),
            pagingSourceFactory = {
                CharactersPagingSource(
                    charactersRemote,
                    ts = ts,
                    hash = hash
                )
            }
        ).flow
            .map {
                it.map(characterMapper::mapFromModel)
            }
    }

    override suspend fun fetchComics(charId: Int): Resource<List<Comic>> {
        return try {
            val response =
                withContext(postExecutionThread.io) {
                    charactersRemote.fetchComics(
                        charId,
                        ts,
                        hash
                    )
                }
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(comicModelMapper.mapModelList(it.data.results))
                } ?: Resource.Error("No Data")
            } else {
                Resource.Error(response.errorBody()?.string() ?: "Something went wrong.")
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    Resource.Error("Timeout")
                }
                is IOException -> {
                    Resource.Error(throwable.localizedMessage ?: "")
                }
                is HttpException -> {
                    val errorResponse = convertErrorBody(throwable)
                    Resource.Error(errorResponse!!)
                }
                else -> {
                    Resource.Error("Unknown Error")
                }
            }

        }
    }

    override suspend fun upsert(character: Character): Long {
        val entity = characterEntityMapper.mapToEntity(character)
        return withContext(postExecutionThread.io) { charactersCache.upsert(entity) }
    }

    override fun characters(): Flow<List<Character>> {
        return charactersCache.characters().mapLatest {
            characterEntityMapper.mapTypeList(it) ?: emptyList()
        }
    }

    override suspend fun delete(character: Character) {
        val entity = characterEntityMapper.mapToEntity(character)
        withContext(postExecutionThread.io) { charactersCache.delete(entity) }
    }

    override suspend fun character(id: Int): Character {
        return withContext(postExecutionThread.io) {
            characterEntityMapper.mapFromEntity(
                charactersCache.character(id)
            )
        }
    }

    private fun convertErrorBody(throwable: HttpException): String? {
        return try {
            throwable.response()?.errorBody()?.string()
        } catch (exception: Exception) {
            "Unknown Error"
        }
    }
}