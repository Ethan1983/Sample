package com.sample.samples.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

class ComposableProvider: CommonComposableProvider() {
    @Composable
    override fun CustomText() {
        var count by remember { mutableIntStateOf(0) }

        LaunchedEffect(Unit) {
            while (true) {
                delay(1000)
                count++
            }
        }

        Text("Full Composable $count")
    }
}
