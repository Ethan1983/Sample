package com.sample.samples

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

private const val FOREGROUND_CHANNEL_ID = "Foreground_Service_Channel_Id"
private const val FOREGROUND_NOTIFICATION_ID = 1234

class SampleForegroundService : Service() {

    override fun onBind(intent: Intent): IBinder = error("Bind unsupported")

    override fun onCreate() {
        super.onCreate()
        createServiceForegroundNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(FOREGROUND_NOTIFICATION_ID, createForegroundNotification())
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createServiceForegroundNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            FOREGROUND_CHANNEL_ID,
            "Foreground Service Channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = "Channel for foreground service notifications"
        notificationManager.createNotificationChannel(channel)
    }

    private fun createForegroundNotification(): Notification {
        val builder = NotificationCompat.Builder(this, FOREGROUND_CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText("Foreground Service")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            .setLocalOnly(true)

        return builder.build()
    }
}