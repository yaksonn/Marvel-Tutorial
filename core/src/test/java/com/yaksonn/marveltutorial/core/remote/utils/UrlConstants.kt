package com.yaksonn.marveltutorial.core.remote.utils

import com.yaksonn.marveltutorial.core.util.Constants.DATE_RANGE
import com.yaksonn.marveltutorial.core.util.Constants.PUBLIC_API_KEY

internal const val LIMIT = 30
internal const val OFFSET = 0
internal const val TS = "ts"
internal const val HASH = "hash"
internal const val CHAR_ID = 1011334


//response
internal const val CHARACTERS_RESPONSE_PATH = "response/characters_response.json"
internal const val EMPTY_CHARACTERS_RESPONSE_PATH = "response/empty_characters_response.json"
internal const val COMICS_RESPONSE_PATH = "response/comics_response.json"


//queries
internal const val CHARACTERS_QUERIES =
    "?apikey=$PUBLIC_API_KEY&ts=$TS&hash=$HASH&offset=$OFFSET&limit=$LIMIT"
internal val RANGE = DATE_RANGE

//paths
internal const val CHARACTERS_FULL_PATH = "$CHARACTERS_PATH$CHARACTERS_QUERIES"
internal const val EMPTY_CHARACTERS_PATH = "$CHARACTERS_PATH?offset=$OFFSET&limit=0"
internal val COMICS_FULL_PATH = "$COMICS_PATH?limit=10&dateRange=$RANGE"