package com.tunahanozatac.movieapp.util.extensions

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
suspend fun <T> StateFlow<T>.listen(action: suspend (value: T) -> Unit) {
    this@listen.collectLatest(action)
}