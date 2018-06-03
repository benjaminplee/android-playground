package com.example.benlee.playground.playgroundnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import java.util.*


private const val DEFAULT_CHANNEL_1 = "DEFAULTCHANNEL1"
private const val HIGH_IMPORTANCE_CHANNEL_2 = "HIGHCHANNEL2"
private const val LOW_IMPORTANCE_CHANNEL_3 = "LOWCHANNEL3"

private const val SMALL_ICON = R.drawable.ic_small_icon
private const val LARGE_ICON = R.drawable.ic_large_icon
private const val TITLE = "The Title"
private const val CONTENT = "This is content text."

fun launchSimplestNotification(context: Context) {
    setupNotificationChannelsForOreoAndHigher(context)

    showNotification(
            NotificationCompat.Builder(context, DEFAULT_CHANNEL_1)
                    .setSmallIcon(SMALL_ICON)
                    .setContentTitle(TITLE)
                    .setContentText(CONTENT)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    )
}

private fun setupNotificationChannelsForOreoAndHigher(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        setupNotificationChannel(DEFAULT_CHANNEL_1, "A Normal Channel", NotificationManager.IMPORTANCE_DEFAULT, "This is a channel for normal stuff", context)
        setupNotificationChannel(HIGH_IMPORTANCE_CHANNEL_2, "An Highly Important Channel", NotificationManager.IMPORTANCE_HIGH, "This is a channel for very important things", context)
        setupNotificationChannel(LOW_IMPORTANCE_CHANNEL_3, "A Low Important Channel", NotificationManager.IMPORTANCE_LOW, "This is a channel for stuff to be ignored", context)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun setupNotificationChannel(channelId: String, name: String?, importance: Int, description: String?, context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    val channel = NotificationChannel(channelId, name, importance)
    channel.description = description
    // Register the channel with the system; you can't change the importance
    // or other notification behaviors after this
    val notificationManager = context.getSystemService(NotificationManager::class.java)
    notificationManager.createNotificationChannel(channel)
}

private fun showNotification(builder: NotificationCompat.Builder): Int {
    val id = Random().nextInt()
    NotificationManagerCompat.from(builder.mContext).notify(id, builder.build())
    return id
}