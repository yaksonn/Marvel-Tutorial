package com.yaksonn.marveltutorial.core.remote.mapper.characters

import com.yaksonn.marveltutorial.core.remote.utils.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class CharacterMapperTest {

    private val mapper = CharacterMapper()

    @Test
    fun `check that map from model maps data correctly`() {
        val dto = DummyData.characterDto
        val character = mapper.mapFromModel(dto)
        Truth.assertThat(character.id).isEqualTo(dto.id)
        Truth.assertThat(character.name).isEqualTo(dto.name)
        Truth.assertThat(character.imageUrl).isEqualTo(mapper.mapImageUrl(dto.thumbnail))
    }
}