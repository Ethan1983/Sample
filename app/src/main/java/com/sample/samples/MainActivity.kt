package com.sample.samples

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.samples.screens.AudioInfo
import com.sample.samples.screens.Displays
import com.sample.samples.screens.MediaSessions
import com.sample.samples.screens.ViewTranslationActivity
import com.sample.samples.ui.theme.SamplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SamplesTheme {
                Scaffold { innerPadding ->
                    Main(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Screen.HOME.route
    ) {
        composable(Screen.HOME.route) {
            Home { screen ->
                navController.navigate(screen.route)
            }
        }
        composable(Screen.DISPLAYS.route) {
            Displays()
        }
        composable(Screen.AUDIO_INFO.route) {
            AudioInfo()
        }
        composable(Screen.MEDIA_SESSIONS.route) {
            MediaSessions()
        }
    }
}

@Composable
fun Home(modifier: Modifier = Modifier, navClick: (Screen) -> Unit) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Button(onClick = { navClick(Screen.DISPLAYS) }) {
                Text(text = "Device Displays")
            }
        }
        item {
            val context = LocalContext.current
            val onClickHandler = {
                context.startActivity(Intent(context, ViewTranslationActivity::class.java))
            }
            Button(onClick = onClickHandler) {
                Text(text = "View translationX")
            }
        }
        item {
            Button(onClick = { navClick(Screen.AUDIO_INFO) }) {
                Text(text = "Audio Info")
            }
        }
        item {
            Button(onClick = { navClick(Screen.MEDIA_SESSIONS) }) {
                Text(text = "Media Sessions")
            }
        }
    }
}
