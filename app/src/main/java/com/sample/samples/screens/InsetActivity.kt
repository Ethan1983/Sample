package com.sample.samples.screens

import android.graphics.Rect
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import com.sample.samples.R

class InsetActivity : AppCompatActivity() {

    private lateinit var displayCutoutContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insets)

        displayCutoutContainer = findViewById(R.id.display_cut_out_container)
    }

    override fun onStart() {
        super.onStart()

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
            val displayCutoutCompat = insets.displayCutout
            displayCutoutContainer.removeAllViews()

            // Check if there is a cutout
            if (displayCutoutCompat != null) {
                v.setPadding(
                    displayCutoutCompat.safeInsetLeft,
                    displayCutoutCompat.safeInsetTop,
                    displayCutoutCompat.safeInsetRight,
                    displayCutoutCompat.safeInsetBottom
                )

                displayCutoutCompat.boundingRects.forEach { rect ->
                    val displayCutoutInfo = ComposeView(this).apply {
                        setContent { DisplayCutoutInfo(rect) }
                    }

                    val width = rect.right - rect.left
                    val height = rect.bottom - rect.top

                    val displayCutout = ComposeView(this).apply {
                        setContent { DisplayCutout() }
                    }

                    displayCutoutContainer.addView(
                        displayCutoutInfo,
                        LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                    )

                    displayCutoutContainer.addView(
                        displayCutout,
                        LinearLayout.LayoutParams(width, height)
                    )
                }
            }

            // Return the insets for further processing by other views
            insets
        }

    }
}

@Composable
fun DisplayCutoutInfo(rect: Rect, modifier: Modifier = Modifier) {
    Box(
        modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = "$rect", fontSize = 24.sp, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun DisplayCutout(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    )
}
