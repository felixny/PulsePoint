package com.example.pulsepoint.core.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * BroadcastReceiver to handle notification dismissal.
 * Prevents reposting dismissed Live Update notifications.
 */
class NotificationDismissReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == PulseNotificationManager.ACTION_NOTIFICATION_DISMISSED) {
            // User dismissed the notification
            // In production, you might want to:
            // 1. Log the dismissal
            // 2. Stop the foreground service
            // 3. Update app state to not repost the notification
            
            // For now, we'll stop the service to prevent automatic reposting
            LiveUpdateService.stop(context)
        }
    }
}
