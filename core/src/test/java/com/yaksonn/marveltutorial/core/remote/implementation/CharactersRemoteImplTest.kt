package com.yaksonn.marveltutorial.core.remote.implementation

import com.yaksonn.marveltutorial.core.remote.abstraction.CharactersRemote
import com.yaksonn.marveltutorial.core.remote.model.characters.CharactersResponse
import com.yaksonn.marveltutorial.core.remote.model.comics.ComicsResponse
import com.yaksonn.marveltutorial.core.remote.utils.*
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class CharactersRemoteImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var charactersRemote: CharactersRemote


    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        charactersRemote = CharactersRemoteImpl(makeApiService(mockWebServer))
    }

    @Test
    fun `check that fetch character returns data successfully`() {
        runBlocking {
            val characters = charactersRemote.fetchCharacters(OFFSET, LIMIT, TS, HASH)
            val response = getCharactersResponse(CHARACTERS_RESPONSE_PATH)
            Truth.assertThat(characters.data.results).isNotEmpty()
            Truth.assertThat(characters.data.results.size).isEqualTo(response.data.results.size)
            Truth.assertThat(characters).isEqualTo(response)
        }
    }

    @Test
    fun `check that fetch characters makes a GET request`() {
        runBlocking {
            charactersRemote.fetchCharacters(OFFSET, LIMIT, TS, HASH)
            Truth.assertThat("GET $CHARACTERS_FULL_PATH HTTP/1.1")
                .isEqualTo(mockWebServer.takeRequest().requestLine)
        }
    }

    @Test
    fun `check that fetch comics returns data successfully`() {
        runBlocking {
            val comics = charactersRemote.fetchComics(CHAR_ID, TS, HASH)
            val response = getComicsResponse(COMICS_RESPONSE_PATH)
            Truth.assertThat(comics).isEqualTo(response)
        }
    }

    @Test
    fun `check that fetch comics makes a GET request`() {
        runBlocking {
            charactersRemote.fetchComics(CHAR_ID, TS, HASH)
            Truth.assertThat("GET $COMICS_FULL_PATH HTTP/1.1")
                .isEqualTo(mockWebServer.takeRequest().requestLine)
        }
    }


    private fun getCharactersResponse(responsePath: String): CharactersResponse {
        return charactersResponseAdapter.fromJson(getJson(responsePath))!!
    }


    private fun getComicsResponse(responsePath: String): ComicsResponse {
        return comicsResponseAdapter.fromJson(getJson(responsePath))!!
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}