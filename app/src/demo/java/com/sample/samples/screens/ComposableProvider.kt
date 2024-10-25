package com.sample.samples.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class ComposableProvider: CommonComposableProvider() {
    @Composable
    override fun CustomText() {
        Text("Demo Composable")
    }
}
