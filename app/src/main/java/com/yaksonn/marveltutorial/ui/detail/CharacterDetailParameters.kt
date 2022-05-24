package com.yaksonn.marveltutorial.ui.detail

import android.os.Parcelable
import androidx.annotation.Keep
import com.yaksonn.marveltutorial.core.domain.model.characters.Character
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class CharacterDetailParameters(
    val character: Character
) : Parcelable