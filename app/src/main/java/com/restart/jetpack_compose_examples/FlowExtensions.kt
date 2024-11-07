package com.restart.jetpack_compose_examples

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


@Composable
fun <T> SharedFlow<T>.Collector(collector: FlowCollector<T>) {
    LaunchedEffect(Unit) {
        collect { event -> collector.emit(event) }
    }
}

inline fun <T> Flow<T>.collectInScope(
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    crossinline collector: suspend CoroutineScope.(T) -> Unit
) {
    coroutineScope.launch {
        collect { event -> this.collector(event) }
    }
}

