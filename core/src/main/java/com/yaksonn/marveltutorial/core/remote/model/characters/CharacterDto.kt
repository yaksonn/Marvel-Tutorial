package com.yaksonn.marveltutorial.core.remote.model.characters

/**
 * Marvel API character network response item.
 *
 * @param id The unique ID of the character resource.
 * @param name The name of the character.
 * @param description A short bio or description of the character.
 * @param thumbnail The representative image for this character.
 */
data class CharacterDto(
    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: ThumbnailDto,
)