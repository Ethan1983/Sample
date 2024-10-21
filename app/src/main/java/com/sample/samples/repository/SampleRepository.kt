package com.sample.samples.repository

import android.util.Log
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

private const val TAG = "SampleRepository"

@ActivityRetainedScoped
class SampleRepository @Inject constructor(){

    init {
        Log.e(TAG, "Sample repository $this initialized")
    }

    fun getSampleValue(): String {
        return "Sample $this"
    }
}
