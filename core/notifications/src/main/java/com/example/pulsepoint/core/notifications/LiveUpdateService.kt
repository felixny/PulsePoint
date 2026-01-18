package com.example.pulsepoint.core.notifications

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.pulsepoint.core.data.repository.TickerRepository
import com.example.pulsepoint.core.network.model.TickerItem
import com.example.pulsepoint.core.ui.theme.AppMode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.sample
import javax.inject.Inject

/**
 * Foreground Service for Live Update Notifications.
 * Synchronizes with TickerRepository to show real-time updates.
 */
@AndroidEntryPoint
class LiveUpdateService : Service() {

    @Inject
    lateinit var tickerRepository: TickerRepository

    @Inject
    lateinit var notificationManager: PulseNotificationManager

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var currentMode: AppMode = AppMode.FINTECH_MODE
    private var currentTickers: List<TickerItem> = emptyList()

    override fun onCreate() {
        super.onCreate()
        Log.d("LiveUpdateService", "Service onCreate - Starting foreground service")
        startForeground(NOTIFICATION_ID, createInitialNotification())
        observeTickers()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Update mode if provided in intent
        intent?.getStringExtra(EXTRA_MODE)?.let { modeName ->
            currentMode = AppMode.valueOf(modeName)
        }

        // Update notification with current state
        if (currentTickers.isNotEmpty()) {
            updateNotification()
        }

        return START_STICKY // Restart service if killed
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    /**
     * Observes ticker updates from the repository.
     * Debounces notification updates to prevent excessive updates (max once per second).
     */
    private fun observeTickers() {
        Log.d("LiveUpdateService", "Starting to observe tickers")
        tickerRepository.observeTickers()
            // Debounce notification updates to max once per second (1000ms)
            // This prevents excessive updates while still keeping data fresh
            .sample(1000) // Sample every 1000ms (1 second) - takes latest value
            .onEach { tickers ->
                Log.d("LiveUpdateService", "Received ${tickers.size} tickers (sampled at 1s interval)")
                currentTickers = tickers
                if (tickers.isNotEmpty()) {
                    updateNotification()
                } else {
                    Log.w("LiveUpdateService", "Tickers list is empty, not updating notification yet")
                }
            }
            .catch { exception ->
                // Log error, notification will show last known state
                Log.e("LiveUpdateService", "Error observing tickers", exception)
                exception.printStackTrace()
            }
            .launchIn(serviceScope)
    }

    /**
     * Updates the notification with latest ticker data.
     */
    private fun updateNotification() {
        try {
            Log.d("LiveUpdateService", "Updating notification with ${currentTickers.size} tickers, mode: $currentMode")
            // Get main activity class from package
            val mainActivityClass = Class.forName("com.example.pulsepoint.MainActivity")
            notificationManager.updateNotification(
                tickers = currentTickers,
                currentMode = currentMode,
                mainActivityClass = mainActivityClass
            )
            Log.d("LiveUpdateService", "Notification updated successfully")
        } catch (e: ClassNotFoundException) {
            Log.e("LiveUpdateService", "MainActivity class not found", e)
            e.printStackTrace()
        } catch (e: Exception) {
            Log.e("LiveUpdateService", "Error updating notification", e)
            e.printStackTrace()
        }
    }

    /**
     * Creates initial notification to start as foreground service.
     */
    private fun createInitialNotification(): android.app.Notification {
        val mainActivityClass = try {
            Class.forName("com.example.pulsepoint.MainActivity")
        } catch (e: ClassNotFoundException) {
            null
        }

        val channelId = PulseNotificationManager.CHANNEL_ID
        val builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("PulsePoint Live Updates") // Required: contentTitle for Live Update
            .setContentText("Initializing real-time updates...")
            .setSmallIcon(getAppIcon())
            .setOngoing(true)
            .setShowWhen(true) // Required for Promoted Ongoing (status chip)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setColorized(false) // Required: Must NOT be colorized

        // Android 16 (API 36+): Request promoted ongoing notification
        if (Build.VERSION.SDK_INT >= 36) {
            try {
                val method = builder.javaClass.getMethod("setRequestPromotedOngoing", Boolean::class.java)
                method.invoke(builder, true)
            } catch (e: Exception) {
                // API not available
            }
        }

        mainActivityClass?.let {
            val intent = Intent(this, it).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                // Pass current mode so activity opens in correct mode
                putExtra(PulseNotificationManager.EXTRA_APP_MODE, currentMode.name)
            }
            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            builder.setContentIntent(pendingIntent)
        }

        return builder.build()
    }

    /**
     * Gets the app icon for notifications.
     * Uses the app icon from the app module.
     */
    private fun getAppIcon(): Int {
        // Use app icon (unnamed.jpg) for notification small icon
        // Priority: ic_pulse_notification_monochrome -> ic_pulse_notification -> PackageManager icon (uses monochrome layer)
        // NOTE: Small notification icons MUST be monochrome (white/transparent) for proper system tinting
        
        // Try ic_pulse_notification_monochrome first (proper monochrome version of unnamed.jpg)
        var iconId = resources.getIdentifier(
            "ic_pulse_notification_monochrome",
            "drawable",
            packageName
        )
        if (iconId != 0) {
            Log.d("LiveUpdateService", "✅ Using ic_pulse_notification_monochrome (based on unnamed.jpg) for small icon, resource ID: $iconId")
            return iconId
        }
        
        // Fallback to ic_pulse_notification (other monochrome vector drawable)
        iconId = resources.getIdentifier(
            "ic_pulse_notification",
            "drawable",
            packageName
        )
        if (iconId != 0) {
            Log.d("LiveUpdateService", "✅ Using ic_pulse_notification (monochrome) for small icon, resource ID: $iconId")
            return iconId
        }
        
        // Try using application icon from PackageManager (ic_launcher adaptive icon)
        try {
            val appInfo = packageManager.getApplicationInfo(packageName, 0)
            if (appInfo.icon != 0) {
                iconId = appInfo.icon
                Log.d("LiveUpdateService", "Using application icon from PackageManager (ic_launcher), resource ID: $iconId")
                return iconId
            }
        } catch (e: Exception) {
            Log.w("LiveUpdateService", "Failed to get app icon from PackageManager", e)
        }
        
        // Fallback to app_icon_drawable (wraps app_icon.jpg which is unnamed.jpg)
        iconId = resources.getIdentifier(
            "app_icon_drawable",
            "drawable",
            packageName
        )
        if (iconId != 0) {
            Log.w("LiveUpdateService", "⚠️ Using app_icon_drawable (full-color JPG) - may not display correctly. Resource ID: $iconId")
            return iconId
        }
        
        // Fallback to direct app_icon reference
        iconId = resources.getIdentifier(
            "app_icon",
            "drawable",
            packageName
        )
        if (iconId != 0) {
            Log.w("LiveUpdateService", "⚠️ Using app_icon (full-color JPG) - may not display correctly. Resource ID: $iconId")
            return iconId
        }
        
        // Fallback to launcher foreground if dedicated icon not found
        if (iconId == 0) {
            iconId = resources.getIdentifier(
                "ic_launcher_foreground",
                "drawable",
                packageName
            )
        }
        
        // If app icon is found, use it; otherwise fallback to system icon
        return if (iconId != 0) {
            Log.d("LiveUpdateService", "✅ Using app icon (unnamed.jpg) for small icon, resource ID: $iconId")
            iconId
        } else {
            Log.w("LiveUpdateService", "❌ App icon not found, using system fallback. Package: $packageName")
            android.R.drawable.ic_menu_info_details
        }
    }

    /**
     * Updates the current mode and refreshes notification.
     */
    fun updateMode(mode: AppMode) {
        currentMode = mode
        if (currentTickers.isNotEmpty()) {
            updateNotification()
        }
    }

    companion object {
        private const val NOTIFICATION_ID = 1001
        const val EXTRA_MODE = "extra_mode"

        /**
         * Starts the LiveUpdateService.
         */
        fun start(context: android.content.Context, mode: AppMode? = null) {
            val intent = Intent(context, LiveUpdateService::class.java).apply {
                mode?.let { putExtra(EXTRA_MODE, it.name) }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                @Suppress("DEPRECATION")
                context.startService(intent)
            }
        }

        /**
         * Stops the LiveUpdateService.
         */
        fun stop(context: android.content.Context) {
            val intent = Intent(context, LiveUpdateService::class.java)
            context.stopService(intent)
        }
    }
}
