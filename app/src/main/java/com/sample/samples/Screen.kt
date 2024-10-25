package com.sample.samples

import android.app.Activity
import com.sample.samples.screens.InsetActivity
import com.sample.samples.screens.MainFeatureScopedDependencyActivity
import com.sample.samples.screens.TouchDelegateActivity
import com.sample.samples.screens.ViewTranslationActivity
import com.sample.samples.screens.WebViewActivity
import kotlinx.serialization.Serializable

val composeScreens = mapOf(
    Displays to R.string.displays,
    AudioInfo to R.string.audio_info,
    MediaSessions to R.string.media_sessions,
    Recomposition to R.string.recompositions,
    CommonFlavorOverride to R.string.common_flavor_override
)

@Serializable
data object Home

@Serializable
data object Displays

@Serializable
data object AudioInfo

@Serializable
data object MediaSessions

@Serializable
data object Recomposition

@Serializable
data object CommonFlavorOverride

enum class ActivityScreen(val title: String, val activityClass: Class<out Activity>) {
    VIEW_TRANSLATION_X("View translationX", ViewTranslationActivity::class.java),
    WINDOW_INSETS("Window Insets", InsetActivity::class.java),
    TOUCH_DELEGATION("Touch Delegate", TouchDelegateActivity::class.java),
    WEB_VIEW("WebView", WebViewActivity::class.java),
    FEATURE_SCOPED_DEPENDENCY("Feature Scoped Hilt Dependency", MainFeatureScopedDependencyActivity::class.java)
}
