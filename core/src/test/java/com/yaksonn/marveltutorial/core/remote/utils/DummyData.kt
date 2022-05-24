package com.yaksonn.marveltutorial.core.remote.utils

import com.yaksonn.marveltutorial.core.cache.model.CharacterEntity
import com.yaksonn.marveltutorial.core.domain.model.characters.Character
import com.yaksonn.marveltutorial.core.remote.model.characters.CharacterDto
import com.yaksonn.marveltutorial.core.remote.model.characters.ThumbnailDto
import com.yaksonn.marveltutorial.core.remote.model.comics.ComicDto
import com.yaksonn.marveltutorial.core.remote.model.comics.ComicsResponse
import com.yaksonn.marveltutorial.core.remote.model.comics.Data
import com.yaksonn.marveltutorial.core.remote.model.comics.Thumbnail

internal object DummyData {

    //network character model
    val characterDto = CharacterDto(
        id = 1011334,
        name = "3-D Man",
        description = "",
        thumbnail = ThumbnailDto(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
            extension = "jpg"
        )
    )

    val comicResponse = ComicsResponse(
        attributionText = "Data provided by Marvel. © 2022 MARVEL",
        attributionHTML = "<a href=\"http://marvel.com\">Data provided by Marvel. © 2022 MARVEL</a>",
        code = 200,
        status = "Ok",
        copyright = "© 2022 MARVEL",
        etag = "3ebce16c2caad4bc0bfb6e952d0cd69eca852024",
        data = Data(
            count = 1,
            limit = 1,
            total = 8,
            offset = 0,
            results = listOf(
                ComicDto(
                    id = 24571,
                    thumbnail = Thumbnail(
                        path = "http://i.annihil.us/u/prod/marvel/i/mg/a/30/4e948fb5e9b52",
                        extension = "jpg"
                    ),
                    title = "Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)"
                )
            )
        )
    )

    val charEntity = CharacterEntity(
        id = 1011334,
        name = "3-D Man",
        description = "",
        imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
    )
    val char = Character(
        id = 1011334,
        name = "3-D Man",
        description = "",
        imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
    )
}