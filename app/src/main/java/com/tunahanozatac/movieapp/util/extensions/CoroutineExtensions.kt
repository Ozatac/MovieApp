package com.tunahanozatac.movieapp.util.extensions

import kotlinx.coroutines.*

fun CoroutineScope.launchOnIO(block: suspend CoroutineScope.() -> Unit): Job {
    return launch(Dispatchers.IO, block = block)
}
