package com.sample.samples.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.sample.samples.SampleApplication

@Composable
fun PerformanceClass(modifier: Modifier = Modifier) {
    val application = LocalContext.current.applicationContext as SampleApplication

    Box (modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Media Performance Class Level: ${application.devicePerformance.mediaPerformanceClass}")
    }
}