package com.example.sampleflow

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher

}

class DefaultDispatchers : DispatcherProvider {
    
    override val main: CoroutineDispatcher
        get() = TODO("Not yet implemented")
    override val io: CoroutineDispatcher
        get() = TODO("Not yet implemented")
    override val default: CoroutineDispatcher
        get() = TODO("Not yet implemented")

}