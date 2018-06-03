package com.example.benlee.playground.playgroundnotifications

import android.content.Context
import android.widget.Toast

fun launchSimpleNotification(context: Context) {
    Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
}