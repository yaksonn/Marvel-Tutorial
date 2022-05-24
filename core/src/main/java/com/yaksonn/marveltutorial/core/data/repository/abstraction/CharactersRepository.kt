package com.yaksonn.marveltutorial.core.data.repository.abstraction

import androidx.paging.PagingData
import com.yaksonn.marveltutorial.core.domain.model.characters.Character
import com.yaksonn.marveltutorial.core.domain.model.comics.Comic
import com.yaksonn.marveltutorial.core.remote.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun fetchCharacters(): Flow<PagingData<Character>>

    suspend fun fetchComics(charId: Int): Resource<List<Comic>>

    suspend fun upsert(character: Character): Long

    fun characters(): Flow<List<Character>>

    suspend fun delete(character: Character)

    suspend fun character(id: Int): Character?
}