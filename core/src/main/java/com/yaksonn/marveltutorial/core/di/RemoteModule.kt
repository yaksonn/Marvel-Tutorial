package com.yaksonn.marveltutorial.core.di

import com.yaksonn.marveltutorial.core.BuildConfig
import com.yaksonn.marveltutorial.core.remote.abstraction.CharactersRemote
import com.yaksonn.marveltutorial.core.remote.implementation.CharactersRemoteImpl
import com.yaksonn.marveltutorial.core.remote.service.ApiService
import com.yaksonn.marveltutorial.core.util.Constants.baseUrl
import com.yaksonn.marveltutorial.factory.RemoteFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface RemoteModule {

    @get:Binds
    val CharactersRemoteImpl.charactersRemote: CharactersRemote

    companion object {
        @[Provides Singleton]
        fun provideApiService(remoteFactory: RemoteFactory): ApiService =
            remoteFactory.createRetrofit(
                url = baseUrl(),
                isDebug = BuildConfig.DEBUG
            )
                .create(ApiService::class.java)
    }
}