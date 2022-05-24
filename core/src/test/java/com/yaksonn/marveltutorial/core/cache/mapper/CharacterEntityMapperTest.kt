package com.yaksonn.marveltutorial.core.cache.mapper

import com.yaksonn.marveltutorial.core.remote.utils.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class CharacterEntityMapperTest {

    private val mapper = CharacterEntityMapper()


    @Test
    fun `check that map from entity maps data correctly`() {
        val entity = DummyData.charEntity
        val domain = mapper.mapFromEntity(entity)
        Truth.assertThat(domain.id).isEqualTo(entity.id)
        Truth.assertThat(domain.imageUrl).isEqualTo(entity.imageUrl)
        Truth.assertThat(domain.description).isEqualTo(entity.description)
        Truth.assertThat(domain.name).isEqualTo(entity.name)
    }

    @Test
    fun `check that map to entity maps data correctly`() {
        val domain = DummyData.char
        val entity = mapper.mapToEntity(domain)
        Truth.assertThat(domain.id).isEqualTo(entity.id)
        Truth.assertThat(domain.imageUrl).isEqualTo(entity.imageUrl)
        Truth.assertThat(domain.description).isEqualTo(entity.description)
        Truth.assertThat(domain.name).isEqualTo(entity.name)
    }
}