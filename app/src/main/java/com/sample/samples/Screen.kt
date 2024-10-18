package com.sample.samples

import androidx.annotation.StringRes

enum class Screen(val route: String, @StringRes val titleResId: Int) {
    HOME("Home", R.string.home),
    DISPLAYS("Displays", R.string.displays),
    AUDIO_INFO("AudioInfo", R.string.audio_info),
    MEDIA_SESSIONS("MediaSessions", R.string.media_sessions),
    RECOMPOSITION("Recomposition", R.string.recompositions)
}
