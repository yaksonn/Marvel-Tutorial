package com.yaksonn.marveltutorial.core.remote.utils

import com.yaksonn.marveltutorial.core.remote.model.characters.CharactersResponse
import com.yaksonn.marveltutorial.core.remote.model.comics.ComicsResponse
import com.yaksonn.marveltutorial.core.remote.service.ApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.ParameterizedType
import java.nio.file.Paths

internal const val CHARACTERS_PATH: String = "/v1/public/characters"
internal const val COMICS_PATH: String = "$CHARACTERS_PATH/$CHAR_ID/comics"
private val okHttpClient: OkHttpClient
    get() = OkHttpClient.Builder().build()

internal val moshi: Moshi
    get() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

internal val charactersResponseAdapter: JsonAdapter<CharactersResponse>
    get() {
        val type: ParameterizedType = Types.newParameterizedType(
            CharactersResponse::class.java,
            String::class.java
        )
        return moshi.adapter(type)
    }

internal val comicsResponseAdapter: JsonAdapter<ComicsResponse>
    get() {
        val type: ParameterizedType = Types.newParameterizedType(
            ComicsResponse::class.java,
            String::class.java
        )
        return moshi.adapter(type)
    }


internal fun getJson(path: String): String {
    val file = Paths.get("src", "test", "resources", path).toFile()
    return String(file.readBytes())
}

internal fun makeApiService(mockWebServer: MockWebServer): ApiService = Retrofit.Builder()
    .baseUrl(mockWebServer.url("/"))
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
    .create(ApiService::class.java)