package com.yaksonn.marveltutorial.core.di

import com.yaksonn.marveltutorial.core.domain.executor.abstraction.PostExecutionThread
import com.yaksonn.marveltutorial.core.domain.executor.impl.PostExecutionThreadImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface ExecutorModule {

    @get:[Binds Singleton]
    val PostExecutionThreadImpl.postExecutionThread: PostExecutionThread
}