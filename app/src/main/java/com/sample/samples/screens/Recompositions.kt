package com.sample.samples.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sample.samples.DebugStateChanges
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
private fun Counter(modifier: Modifier) {
    var counter by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (counter < 2) {
            delay(1.seconds)
            counter++
        }
    }

    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Counter $counter")

        Spacer(modifier.padding(8.dp))

        InnerLogCounter()
    }
}

@Composable
private fun InnerLogCounter(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (counter < 5) {
            delay(1.seconds)
            counter++
        }
    }

    Log.e("InnerLogCounter", "Inner Counter is $counter")

    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = "Inner Counter")
    }
}

@Composable
fun CounterDebug(modifier: Modifier = Modifier) {
    DebugStateChanges("Counter") {
        Counter(modifier)
    }
}
