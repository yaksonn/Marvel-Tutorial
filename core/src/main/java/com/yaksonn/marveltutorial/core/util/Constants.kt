package com.yaksonn.marveltutorial.core.util

import com.yaksonn.marveltutorial.core.BuildConfig

object Constants {

    //Base url
    fun baseUrl() = BuildConfig.BASE_URL

    /* endpoints */
    const val CHARACTERS = "/v1/public/characters"
    const val CHARACTERS_COMICS = "$CHARACTERS/{characterId}/comics"

    //load size
    const val LIMIT = 30

    const val IMAGE_URL_FORMAT = "%s.%s"
    const val DATE_RANGE_FORMAT = "%s,%s"
    internal const val HASH_FORMAT = "%s%s%s"

    const val START_DATE = "2005-01-01"
    const val END_DATE = "2022-01-01"
    val DATE_RANGE = DATE_RANGE_FORMAT.format(
        START_DATE,
        END_DATE
    )

    //api keys
    internal const val PUBLIC_API_KEY = "c703e466258bbc4fdcce04de90604723"
    internal const val PRIVATE_API_KEY = "a744ba9fe17872dfd9b4101d47aa0f85bfd7c434"

}