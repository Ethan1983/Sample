package com.sample.samples

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.MutableSnapshot
import androidx.compose.runtime.snapshots.Snapshot
import java.util.WeakHashMap

@PublishedApi
internal class DebugStateObservation(private val id: String) {
    private val map = WeakHashMap<Any, MutableList<Exception>>()
    val readObserver: (Any) -> Unit = {
        synchronized(this) {
            val e = Exception()
            val list = map.getOrPut(it) { mutableListOf() }
            list += e
        }
    }
    fun print(changes: Set<Any>) {
        synchronized(this) {
            val affected = map.keys.intersect(changes)
            if (affected.isNotEmpty()) {
                affected.forEach {
                    printStateChange(id, it, map[it])
                }
            }
        }
    }
    fun clear() {
        synchronized(this) {
            map.clear()
        }
    }
}

private fun printStateChange(id: String, state: Any, exceptions: List<Exception>?) {
    val traces = exceptions?.joinToString(separator = "\n") {
        // remove trace start, sample:
        // at androidx.compose.foundation.demos.DebugStateObservation$readObserver$1.invoke(Test.kt:33)
        // at androidx.compose.foundation.demos.DebugStateObservation$readObserver$1.invoke(Test.kt:31)
        // at androidx.compose.runtime.snapshots.SnapshotKt$mergedReadObserver$1.invoke(Snapshot.kt:1771)
        // at androidx.compose.runtime.snapshots.SnapshotKt$mergedReadObserver$1.invoke(Snapshot.kt:1770)
        // at androidx.compose.runtime.snapshots.SnapshotKt.readable(Snapshot.kt:2003)
        // at androidx.compose.runtime.SnapshotMutableIntStateImpl.getIntValue(SnapshotIntState.kt:138)
        val stackTrace = it.stackTrace
        buildString {
            for (i in 6.. minOf(10, stackTrace.size)) {
                append("\tat ${it.stackTrace[i]}")
                append("\n")
            }
            append("...")
        }
    } ?: ""
    println("$id might recompose because $state changed, last read at:\n$traces")
}

/**
 * Records state observations inside @Composable [block] and prints to [System.out] whenever
 * state mutation is applied.
 *
 * NOTE: This doesn't record recompositions precisely and only uses snapshot system to record state
 * mutations that /might/ invalidate recomposition. Consecutive invocations might result in
 * different results depending on functions that were run / skipped during each execution. To be
 * used directly inside a function scope that recomposes, as Compose might skip inner scopes and
 * reads/mutations are not going to be recorded.
 */
@Composable
inline fun DebugStateChanges(id: String, block: @Composable () -> Unit) {
    val observation = remember { DebugStateObservation(id) }
    val currentSnapshot = Snapshot.current
    val snapshot = if (currentSnapshot is MutableSnapshot) {
        currentSnapshot.takeNestedMutableSnapshot(observation.readObserver)
    } else {
        currentSnapshot.takeNestedSnapshot(observation.readObserver)
    }
    DisposableEffect(observation) {
        val disposeHandle = Snapshot.registerApplyObserver { changes, _ ->
            observation.print(changes)
        }
        onDispose {
            observation.clear()
            disposeHandle.dispose()
        }
    }
    observation.clear()
    snapshot.runAndDispose { block() }
}

// Compose doesn't work with try/finally, but we don't really use it for catching things.
@PublishedApi
internal inline fun <T> Snapshot.runAndDispose(block: () -> T): T =
    try {
        enter(block)
    } finally {
        dispose()
    }