package com.sample.samples.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

private val composeProvider = ComposableProvider()

@Composable
fun CommonFlavorText(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        composeProvider.CustomText()
    }
}

open class CommonComposableProvider {
    @Composable
    open fun CustomText() {
        Text("Main Composable")
    }
}
