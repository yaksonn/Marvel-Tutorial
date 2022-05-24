package com.yaksonn.marveltutorial.core.remote.abstraction

import com.yaksonn.marveltutorial.core.remote.model.characters.CharactersResponse
import com.yaksonn.marveltutorial.core.remote.model.comics.ComicsResponse
import retrofit2.Response

interface CharactersRemote {

    suspend fun fetchCharacters(
        offset: Int,
        limit: Int,
        ts:String,
        hash:String
    ): CharactersResponse

    suspend fun fetchComics(
        id: Int,
        ts:String,
        hash:String
    ): Response<ComicsResponse>
}