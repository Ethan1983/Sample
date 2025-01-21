package com.sample.samples.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter

@Composable
fun CoilImage(modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(model = "https://picsum.photos/3000/2000")
    val state = painter.state.collectAsStateWithLifecycle()

    when (state.value) {
        is AsyncImagePainter.State.Success -> {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = modifier.fillMaxSize()
            )
        }
        is AsyncImagePainter.State.Empty -> {
            Text("Empty ${state.value}", modifier = modifier.fillMaxSize().background(Color.Red))
        }
        is AsyncImagePainter.State.Loading -> {
            Text("Loading ${state.value}", modifier = modifier.fillMaxSize().background(Color.Yellow))
        }
        is AsyncImagePainter.State.Error -> {
            Text("Error ${state.value}", modifier = modifier.fillMaxSize().background(Color.Blue))
        }
    }
}
