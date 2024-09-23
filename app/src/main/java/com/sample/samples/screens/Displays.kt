package com.sample.samples.screens

import android.content.Context
import android.hardware.display.DisplayManager
import android.view.Display
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun Displays(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    val displays = displayManager.displays

    Box(modifier.fillMaxSize()) {
        LazyColumn {
            items(displays.size) { index ->
                val display = displays[index]
                Display(display)
            }
        }
    }
}

@Composable
fun Display(display: Display, modifier: Modifier = Modifier) {
    Card(modifier.fillMaxWidth().padding(24.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Display Id: ${display.displayId}")
            Text(text = "Display Name: ${display.name}")
            Text(text = "Display isValid: ${display.isValid}")
            Text(text = "Display refreshRate: ${display.refreshRate}")
            Text(text = "Display supportedModes size: ${display.supportedModes.size}")
            Text(text = "Display mode: ${display.mode}")
            Text(text = "Display flags: ${display.flags}")
            Text(text = "Display state: ${display.state}")
            Text(text = "Display rotation: ${display.rotation}")
        }
    }
}
