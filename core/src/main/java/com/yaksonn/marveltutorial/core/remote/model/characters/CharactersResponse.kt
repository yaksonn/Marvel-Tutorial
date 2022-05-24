package com.yaksonn.marveltutorial.core.remote.model.characters

data class CharactersResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Any,
    val copyright: String,
    val data: DataDto,
    val etag: String,
    val status: String
)