package com.yaksonn.marveltutorial.core.cache.implementation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yaksonn.marveltutorial.core.cache.DummyData
import com.yaksonn.marveltutorial.core.cache.abstraction.CharactersCache
import com.yaksonn.marveltutorial.core.cache.db.MarvelDb
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(
    AndroidJUnit4::class
)
class CharactersCacheImplTest {

    private lateinit var marvelDb: MarvelDb

    private lateinit var cache: CharactersCache

    @Before
    fun setup() {
        marvelDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MarvelDb::class.java
        )
            .allowMainThreadQueries().build()

        cache = CharactersCacheImpl(marvelDb.charactersDao)
    }

    @Test
    fun upsert_insertsData_when_dataIsNotInCache() {
        runBlocking {
            val entity = DummyData.charEntity
            val entities = cache.characters().first()
            Truth.assertThat(entities).isEmpty()
            cache.upsert(entity)
            val newEntities = cache.characters().first()
            Truth.assertThat(newEntities).isNotNull()
            Truth.assertThat(newEntities.size).isEqualTo(1)
            Truth.assertThat(newEntities.first().id).isEqualTo(entity.id)
        }
    }

    @Test
    fun upsert_updatesData_when_dataIsAlreadyInCache() {
        runBlocking {
            val entity = DummyData.charEntity
            cache.upsert(entity)
            val entities = cache.characters().first()
            Truth.assertThat(entities).isNotEmpty()
            val newEntity = entity.copy(name = "Dummy")
            cache.upsert(newEntity)
            val newData = cache.characters().first()
            Truth.assertThat(newData).isNotEmpty()
            Truth.assertThat(newData.first().id).isEqualTo(entity.id)
            Truth.assertThat(newData.first().name).isNotEqualTo(entity.name)
        }
    }

    @Test
    fun delete_deletesData_correctly() {
        runBlocking {
            val entity = DummyData.charEntity
            cache.upsert(entity)
            Truth.assertThat(cache.characters().first()).isNotEmpty()
            cache.delete(entity)
            Truth.assertThat(cache.characters().first()).isEmpty()
        }
    }

    @Test
    fun character_getsData_correctly() {
        runBlocking {
            val entity = cache.character(1)
            Truth.assertThat(entity).isNull()
            val newEntity = DummyData.charEntity
            cache.upsert(newEntity)
            val new = cache.character(newEntity.id)
            Truth.assertThat(new).isNotNull()
        }
    }


    @After
    fun tearDown() {
        marvelDb.close()
    }
}