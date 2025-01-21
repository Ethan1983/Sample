package com.sample.samples

import android.app.Application
import androidx.core.performance.DefaultDevicePerformance
import androidx.core.performance.DevicePerformance
import androidx.core.performance.play.services.PlayServicesDevicePerformance
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication: Application() {
    lateinit var devicePerformance: DevicePerformance

    override fun onCreate() {
        super.onCreate()
        devicePerformance = DefaultDevicePerformance()
    }
}
