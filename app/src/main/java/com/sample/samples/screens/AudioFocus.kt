package com.sample.samples.screens

import android.content.Context
import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AudioFocus(modifier: Modifier = Modifier) {
    val audioManager = LocalContext.current.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    var currentState by remember { mutableStateOf("Unknown") }

    val focusChangeListener = remember {
        OnAudioFocusChangeListener { focusChange ->
            currentState = when (focusChange) {
                AudioManager.AUDIOFOCUS_GAIN -> "Audio Focus Gain"
                AudioManager.AUDIOFOCUS_LOSS -> "Audio Focus Loss"
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> "Audio Focus Loss Transient"
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> "Audio Focus Loss Transient Can Duck"
                else -> "Unknown Audio Focus"
            }
        }
    }

    val requestAudioFocusHandler = remember {
        {
            val result = audioManager.requestAudioFocus(
                focusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN
            )

            currentState = when (result) {
                AudioManager.AUDIOFOCUS_REQUEST_FAILED -> "Request Failed"
                AudioManager.AUDIOFOCUS_REQUEST_GRANTED -> "Request Granted"
                AudioManager.AUDIOFOCUS_REQUEST_DELAYED -> "Request Delayed"
                else -> "Unknown Request response"
            }
        }
    }

    val abandonAudioFocusHandler = remember {
        {
            val result = audioManager.abandonAudioFocus(focusChangeListener)

            currentState = when (result) {
                AudioManager.AUDIOFOCUS_REQUEST_FAILED -> "Abandon Failed"
                AudioManager.AUDIOFOCUS_REQUEST_GRANTED -> "Abandon Granted"
                AudioManager.AUDIOFOCUS_REQUEST_DELAYED -> "Abandon Delayed"
                else -> "Unknown Abandon response"
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(requestAudioFocusHandler) {
            Text("Request Audio Focus")
        }

        Spacer(modifier.height(16.dp))

        Button(abandonAudioFocusHandler) {
            Text("Abandon Audio Focus")
        }

        Spacer(modifier.height(16.dp))

        Text("Current State is $currentState")
    }
}
