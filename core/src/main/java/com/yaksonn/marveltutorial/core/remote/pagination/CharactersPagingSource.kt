package com.yaksonn.marveltutorial.core.remote.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yaksonn.marveltutorial.core.remote.abstraction.CharactersRemote
import com.yaksonn.marveltutorial.core.remote.model.characters.CharacterDto
import com.yaksonn.marveltutorial.core.util.Constants.LIMIT
import retrofit2.HttpException
import java.io.IOException

class CharactersPagingSource constructor(
    private val charactersRemote: CharactersRemote,
    private val ts: String,
    private val hash: String
) : PagingSource<Int, CharacterDto>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        return try {
            val offset = params.key ?: 0
            val response =
                charactersRemote.fetchCharacters(
                    offset = offset,
                    limit = params.loadSize,
                    ts = ts,
                    hash = hash
                )
            val responseOffset = response.data.offset
            val totalChars = response.data.total

            LoadResult.Page(
                data = response.data.results,
                prevKey = if (offset == 0) null else responseOffset.minus(LIMIT),
                nextKey = if (response.data.results.isEmpty() || responseOffset >= totalChars) null else responseOffset + LIMIT
            )
        } catch (e: Exception) {
            when (e) {
                is HttpException, is IOException -> {
                    LoadResult.Error(e)
                }
                else -> {
                    e.printStackTrace()
                    LoadResult.Error(Error("Something went wrong."))
                }
            }
        }
    }
}