package com.yaksonn.marveltutorial.core.remote.implementation

import com.yaksonn.marveltutorial.core.remote.abstraction.CharactersRemote
import com.yaksonn.marveltutorial.core.remote.model.characters.CharactersResponse
import com.yaksonn.marveltutorial.core.remote.model.comics.ComicsResponse
import com.yaksonn.marveltutorial.core.remote.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class CharactersRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRemote {

    override suspend fun fetchCharacters(
        offset: Int,
        limit: Int,
        ts: String,
        hash: String
    ): CharactersResponse {
        return apiService.fetchCharacters(
            ts = ts,
            hash = hash,
            offset = offset,
            limit = limit
        )
    }

    override suspend fun fetchComics(id: Int, ts: String, hash: String): Response<ComicsResponse> {
        return apiService.fetchComics(id, ts = ts, hash = hash)
    }
}