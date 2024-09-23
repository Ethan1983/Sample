package com.sample.samples.screens

import android.content.Context
import android.media.MediaMetadata
import android.media.session.MediaController
import android.media.session.MediaSessionManager
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
fun MediaSessions(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val mediaSessionManager =
        context.getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager

    val activeSessions: List<MediaController> = mediaSessionManager.getActiveSessions(null)

    Box(modifier.fillMaxSize()) {
        LazyColumn {
            items(activeSessions.size) { index ->
                MediaSession(activeSessions[index])
            }
        }
    }
}

@Composable
fun MediaSession(session: MediaController, modifier: Modifier = Modifier) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Package: ${session.getPackageName()}")
            Text(text = "State: ${session.playbackState}")
            Text(text = "Playback State: ${session.playbackState?.state}")
            Text(text = "Title: ${session.metadata?.getText(MediaMetadata.METADATA_KEY_TITLE)}")
            Text(text = "Album: ${session.metadata?.getText(MediaMetadata.METADATA_KEY_ALBUM)}")
            Text(text = "Artist: ${session.metadata?.getText(MediaMetadata.METADATA_KEY_ARTIST)}")
        }
    }
}
