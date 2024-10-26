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
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.samples.screens.AudioFocus
import com.sample.samples.screens.AudioInfo
import com.sample.samples.screens.AutoScrollList
import com.sample.samples.screens.CommonFlavorText
import com.sample.samples.screens.CounterDebug
import com.sample.samples.screens.Displays
import com.sample.samples.screens.MediaSessions
import com.sample.samples.screens.RotatingBox
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
        startDestination = Home
    ) {
        composable<Home> {
            Home { screen ->
                navController.navigate(screen)
            }
        }
        composable<Displays> {
            Displays()
        }
        composable<AudioInfo> {
            AudioInfo()
        }
        composable<MediaSessions> {
            MediaSessions()
        }
        composable<Recomposition> {
            CounterDebug()
        }
        composable<CommonFlavorOverride> {
            CommonFlavorText()
        }
        composable<RotateAnimation> {
            RotatingBox()
        }
        composable<AutoScrollList> {
            AutoScrollList()
        }
        composable<AudioFocus> {
            AudioFocus()
        }
    }
}

@Composable
fun Home(modifier: Modifier = Modifier, navClick: (Any) -> Unit) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActivityScreen.entries.forEach { activityScreen ->
            item {
                val context = LocalContext.current
                val onClickHandler = {
                    context.startActivity(Intent(context, activityScreen.activityClass))
                }
                Button(onClick = onClickHandler) {
                    Text(text = activityScreen.title)
                }
            }
        }

        composeScreens.forEach { (screen, titleResId) ->
            item {
                Button(onClick = { navClick(screen) }) {
                    Text(text = stringResource(titleResId))
                }
            }
        }
    }
}
