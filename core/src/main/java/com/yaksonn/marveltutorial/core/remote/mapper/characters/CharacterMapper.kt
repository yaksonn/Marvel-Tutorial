package com.yaksonn.marveltutorial.core.remote.mapper.characters

import com.yaksonn.marveltutorial.core.domain.model.characters.Character
import com.yaksonn.marveltutorial.core.remote.model.characters.CharacterDto
import com.yaksonn.marveltutorial.core.remote.model.characters.ThumbnailDto
import com.yaksonn.marveltutorial.core.util.Constants.IMAGE_URL_FORMAT
import com.yaksonn.marveltutorial.mapper.RemoteModelMapper
import javax.inject.Inject

class CharacterMapper @Inject constructor() : RemoteModelMapper<CharacterDto, Character> {
    override fun mapFromModel(model: CharacterDto): Character {
        return Character(
            id = model.id,
            name = model.name,
            imageUrl = mapImageUrl(model.thumbnail),
            description = model.description
        )
    }

    internal fun mapImageUrl(thumbnailDto: ThumbnailDto): String {
        return IMAGE_URL_FORMAT.format(
            thumbnailDto.path.replace("http", "https"),
            thumbnailDto.extension
        )
    }
}