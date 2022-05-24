package com.yaksonn.marveltutorial.core.di

import com.yaksonn.marveltutorial.core.cache.abstraction.CharactersCache
import com.yaksonn.marveltutorial.core.cache.implementation.CharactersCacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@[Module InstallIn(SingletonComponent::class)]
interface CacheModule {

    @get:Binds
    val CharactersCacheImpl.charactersCache: CharactersCache
}