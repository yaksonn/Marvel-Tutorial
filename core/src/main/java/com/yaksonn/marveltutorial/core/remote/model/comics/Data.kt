package com.yaksonn.marveltutorial.core.remote.model.comics

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ComicDto>,
    val total: Int
)