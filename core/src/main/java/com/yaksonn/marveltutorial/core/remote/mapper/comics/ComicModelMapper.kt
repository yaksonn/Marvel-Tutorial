package com.yaksonn.marveltutorial.core.remote.mapper.comics

import com.yaksonn.marveltutorial.core.domain.model.comics.Comic
import com.yaksonn.marveltutorial.core.remote.model.comics.ComicDto
import com.yaksonn.marveltutorial.core.remote.model.comics.Thumbnail
import com.yaksonn.marveltutorial.core.util.Constants
import com.yaksonn.marveltutorial.mapper.RemoteModelMapper
import javax.inject.Inject

class ComicModelMapper @Inject constructor() : RemoteModelMapper<ComicDto, Comic> {
    override fun mapFromModel(model: ComicDto): Comic {
        return Comic(
            id = model.id,
            image = mapImageUrl(model.thumbnail),
            title = model.title,
        )
    }

    private fun mapImageUrl(thumbnailDto: Thumbnail): String {
        return Constants.IMAGE_URL_FORMAT.format(
            thumbnailDto.path.replace("http", "https"),
            thumbnailDto.extension
        )
    }
}