package com.yaksonn.marveltutorial.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object RemoteModule {

    @get:[Provides Singleton]
    val provideMoshi: Moshi
        get() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}