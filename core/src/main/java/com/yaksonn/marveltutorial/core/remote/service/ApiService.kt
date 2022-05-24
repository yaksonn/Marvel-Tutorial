package com.yaksonn.marveltutorial.core.remote.service

import com.yaksonn.marveltutorial.core.remote.model.characters.CharactersResponse
import com.yaksonn.marveltutorial.core.remote.model.comics.ComicsResponse
import com.yaksonn.marveltutorial.core.util.Constants
import com.yaksonn.marveltutorial.core.util.Constants.DATE_RANGE
import com.yaksonn.marveltutorial.core.util.Constants.PUBLIC_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.CHARACTERS)
    suspend fun fetchCharacters(
        @Query("apikey")key:String = PUBLIC_API_KEY,
        @Query("ts") ts:String,
        @Query("hash")hash:String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): CharactersResponse

    @GET(Constants.CHARACTERS_COMICS)
    suspend fun fetchComics(
        @Path("characterId") id: Int,
        @Query("apikey")key:String = PUBLIC_API_KEY,
        @Query("ts")ts:String,
        @Query("hash")hash:String,
        @Query("limit") limit: Int = 10,
        @Query("dateRange", encoded = true) date: String = DATE_RANGE,
    ): Response<ComicsResponse>
}