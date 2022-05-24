package com.yaksonn.marveltutorial.core.remote.model.characters

data class DataDto(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<CharacterDto>,
    val total: Int
)