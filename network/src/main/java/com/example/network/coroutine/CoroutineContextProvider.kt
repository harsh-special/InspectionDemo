package com.example.network.coroutine

import kotlin.coroutines.CoroutineContext

interface Dispatcher {
    val main: CoroutineContext
    val io: CoroutineContext
}