package com.yaksonn.marveltutorial.core.domain.model.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description:String
) : Parcelable