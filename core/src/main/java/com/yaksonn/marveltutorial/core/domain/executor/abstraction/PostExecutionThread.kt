package com.yaksonn.marveltutorial.core.domain.executor.abstraction

import kotlinx.coroutines.CoroutineDispatcher

interface PostExecutionThread {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}