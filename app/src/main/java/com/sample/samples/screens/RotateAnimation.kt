package com.sample.samples.screens

import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

private const val TAG = "RotatingComposeAnimation"

@Composable
fun RotatingBox() {
    var graphicsLayerAnimation by rememberSaveable { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button({ graphicsLayerAnimation = !graphicsLayerAnimation }) {
            Text("Toggle Graphics/Composition animation")
        }

        Spacer(Modifier.height(16.dp))

        if (graphicsLayerAnimation) {

            Text("Animation using graphics layer")

            Spacer(Modifier.height(16.dp))

            RotatingGraphicsBox()
        } else {

            Text("Animation using compositions")

            Spacer(Modifier.height(16.dp))

            RotatingComposeBox()
        }
    }
}

@Composable
fun RotatingComposeBox() {
    val transition = rememberInfiniteTransition("Infinite Transition")
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000)
        ),
        label = "Float Animation"
    )

    Log.d(TAG, "RotatingComposeBox recomposed")

    Box(
        modifier = Modifier
            .rotate(rotation * 360f)
            .size(100.dp)
            .background(Color.Red)
    )
}

@Composable
fun RotatingGraphicsBox() {
    val transition = rememberInfiniteTransition("Infinite Transition")
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000)
        ),
        label = "Float Animation"
    )

    Log.d(TAG, "RotatingGraphicsBox recomposed")

    Box(
        modifier = Modifier
            .graphicsLayer { rotationZ = rotation * 360f }
            .size(100.dp)
            .background(Color.Blue)
    )
}
