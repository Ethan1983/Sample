package com.sample.samples.screens

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.RelativeLayout
import android.widget.RelativeLayout.ALIGN_PARENT_TOP
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sample.samples.R

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var heightAnimator: ValueAnimator? = null
    private var translateXAnimator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_webview)

        webView = findViewById<WebView?>(R.id.webview).apply {
            settings.javaScriptEnabled = true
            loadUrl("https://archive.org/details/BigBuckBunny_328")
        }

        findViewById<View>(R.id.button).setOnClickListener {
            animateWebView()
        }
    }

    private fun animateWebView() {
        val animationDuration = 5000L
        heightAnimator?.cancel()
        translateXAnimator?.cancel()

        heightAnimator = ValueAnimator.ofInt(webView.height, webView.height * 2).apply {
            addUpdateListener { animation ->
                val animatedHeight = animation.animatedValue as Int

                webView.layoutParams = RelativeLayout.LayoutParams(webView.width, animatedHeight).apply {
                    addRule(ALIGN_PARENT_TOP)
                }
            }

            setDuration(animationDuration)
            start()
        }

        translateXAnimator = ObjectAnimator.ofFloat(webView, "translationX", 0f, 300f).apply {
            duration = animationDuration
            start()
        }
    }
}
