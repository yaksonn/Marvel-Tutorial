package com.yaksonn.marveltutorial.core.remote.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class RequestDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            CHARACTERS_FULL_PATH -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(CHARACTERS_RESPONSE_PATH))
            }
            EMPTY_CHARACTERS_PATH -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(EMPTY_CHARACTERS_RESPONSE_PATH))
            }
            COMICS_FULL_PATH -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(COMICS_RESPONSE_PATH))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path}")
        }
    }
}