package com.example.countries.util.dispatcher_provider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val mainImmediate: CoroutineDispatcher
        get() = Dispatchers.Main.immediate

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default

    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
