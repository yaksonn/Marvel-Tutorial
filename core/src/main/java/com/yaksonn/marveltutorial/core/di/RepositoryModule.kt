package com.yaksonn.marveltutorial.core.di

import com.yaksonn.marveltutorial.core.data.repository.abstraction.CharactersRepository
import com.yaksonn.marveltutorial.core.data.repository.implementation.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface RepositoryModule {

    @get:Binds
    val CharactersRepositoryImpl.characterRepo: CharactersRepository
}