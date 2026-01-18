package com.example.pulsepoint.core.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.pulsepoint.core.network.model.TickerItem
import com.example.pulsepoint.core.ui.theme.AppMode
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Notification Manager for PulsePoint Live Updates.
 * Handles industry-specific notification styles with dynamic theming.
 */
@Singleton
class PulseNotificationManager @Inject constructor(
    private val context: Context
) {
    private val notificationManager = NotificationManagerCompat.from(context)

    companion object {
        const val CHANNEL_ID = "live_updates_channel"
        private const val CHANNEL_NAME = "Live Updates"
        const val NOTIFICATION_ID = 1001
        private const val REQUEST_CODE = 100
        const val ACTION_NOTIFICATION_DISMISSED = "com.example.pulsepoint.NOTIFICATION_DISMISSED"
        const val EXTRA_APP_MODE = "com.example.pulsepoint.EXTRA_APP_MODE"
    }

    init {
        createNotificationChannel()
    }

    /**
     * Creates a high-priority notification channel for live updates.
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH // High priority for live updates
            ).apply {
                description = "Real-time updates for PulsePoint data"
                enableLights(true)
                enableVibration(false)
                setShowBadge(true)
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    /**
     * Shows or updates the notification based on the current mode and ticker data.
     * 
     * @param tickers List of current ticker items
     * @param currentMode Current app mode (Fintech, Sports, Rewards)
     * @param mainActivityClass The main activity class for the PendingIntent
     */
    fun updateNotification(
        tickers: List<TickerItem>,
        currentMode: AppMode,
        mainActivityClass: Class<*>
    ) {
        // Log for debugging
        android.util.Log.d("PulseNotificationManager", "updateNotification called with ${tickers.size} tickers, mode: $currentMode")
        
        if (tickers.isEmpty()) {
            android.util.Log.w("PulseNotificationManager", "Tickers list is empty, showing default notification")
            // Show a default notification even if tickers are empty
            showDefaultNotification(currentMode, mainActivityClass)
            return
        }

        val appMode = currentMode
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(getSmallIcon(appMode))
            // Remove large icon - user wants only small icon (unnamed.jpg) on the left
            // Live Update Requirements:
            .setOngoing(true) // FLAG_ONGOING_EVENT - Required for Live Updates
            .setShowWhen(true) // Required for Promoted Ongoing (status chip)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(createPendingIntent(mainActivityClass, currentMode))
            // Live Update Requirements: Must NOT be colorized
            .setColorized(false)
            // Live Update Requirements: Must NOT be group summary
            // (not setting setGroupSummary, defaults to false)
            // Live Update Requirements: Must have contentTitle (set by style methods)

        // Android 16 (API 36+): Request promoted ongoing notification
        // Required for Live Update promotion - Promoted Ongoing notifications
        // Note: These APIs may not exist in current androidx.core versions, so we gracefully handle missing methods
        if (Build.VERSION.SDK_INT >= 36) {
            try {
                val method = builder.javaClass.getMethod("setRequestPromotedOngoing", Boolean::class.java)
                method.invoke(builder, true)
                android.util.Log.d("PulseNotificationManager", "setRequestPromotedOngoing(true) called for Android 16+")
            } catch (e: NoSuchMethodException) {
                // Expected on devices/emulators with older androidx.core versions
                // Only log at debug level, not warning, since this is expected
                android.util.Log.d("PulseNotificationManager", "setRequestPromotedOngoing not available (Android 16+ API not in androidx.core yet)")
            } catch (e: Exception) {
                // Other exceptions are unexpected, log as warning
                android.util.Log.w("PulseNotificationManager", "Error calling setRequestPromotedOngoing", e)
            }
        }

        // Set delete intent to detect when user dismisses the notification
        builder.setDeleteIntent(createDeleteIntent())

        // Apply industry-specific style
        when (appMode) {
            AppMode.FINTECH_MODE -> applyFintechStyle(builder, tickers)
            AppMode.SPORTS_MODE -> applySportsStyle(builder, tickers)
            AppMode.REWARDS_MODE -> applyRewardsStyle(builder, tickers)
        }

        // Android 16+: Set short critical text for status bar chip (max 7 chars)
        // This must be set AFTER style is applied to ensure it's not overridden
        // Note: These APIs may not exist in current androidx.core versions, so we gracefully handle missing methods
        if (Build.VERSION.SDK_INT >= 36) {
            try {
                val shortStatusText = getShortStatusText(appMode, tickers)
                if (shortStatusText.isNotEmpty() && shortStatusText.length <= 7) {
                    val setShortCriticalTextMethod = builder.javaClass.getMethod("setShortCriticalText", CharSequence::class.java)
                    setShortCriticalTextMethod.invoke(builder, shortStatusText)
                    android.util.Log.d("PulseNotificationManager", "Status chip text set: '$shortStatusText'")
                }
            } catch (e: NoSuchMethodException) {
                // Expected on devices/emulators with older androidx.core versions
                // Only log at debug level, not warning, since this is expected
                android.util.Log.d("PulseNotificationManager", "setShortCriticalText not available (Android 16+ API not in androidx.core yet)")
            } catch (e: Exception) {
                // Other exceptions are unexpected, log as warning
                android.util.Log.w("PulseNotificationManager", "Error calling setShortCriticalText", e)
            }
        }

        // Check notification permission for Android 13+ (API 33+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                android.util.Log.w("PulseNotificationManager", "POST_NOTIFICATIONS permission not granted")
                // Permission not granted, but we'll still try to show notification
                // as foreground services can show notifications without permission
            } else {
                android.util.Log.d("PulseNotificationManager", "POST_NOTIFICATIONS permission granted")
            }
        }
        
        val notification = builder.build()
        // Verify the icon is actually set in the notification
        val iconResId = notification.smallIcon?.resId ?: 0
        android.util.Log.d("PulseNotificationManager", "Posting notification with ID: $NOTIFICATION_ID, icon resource ID: $iconResId")
        if (iconResId == 0) {
            android.util.Log.e("PulseNotificationManager", "⚠️ WARNING: Notification has no icon set!")
        }
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    /**
     * Fintech Mode: BigTextStyle for crypto price and volatility.
     * Compliant with Live Update requirements: Ongoing, user-initiated, time-sensitive.
     */
    private fun applyFintechStyle(
        builder: NotificationCompat.Builder,
        tickers: List<TickerItem>
    ) {
        val primaryTicker = tickers.firstOrNull() ?: return
        val trend = if (primaryTicker.isPositiveChange) "↑" else "↓"
        val trendColor = if (primaryTicker.isPositiveChange) "Green" else "Red"
        
        val bigText = buildString {
            append("${primaryTicker.symbol}/USD: $${String.format("%.2f", primaryTicker.currentValue)}\n")
            append("Change: ${primaryTicker.changePercentFormatted} $trend ($trendColor)\n")
            append("Volatility: ${String.format("%.2f%%", kotlin.math.abs(primaryTicker.changePercent))}\n")
            if (tickers.size > 1) {
                append("\nOther pairs:")
                tickers.take(3).forEach { ticker ->
                    append("\n• ${ticker.symbol}: $${String.format("%.2f", ticker.currentValue)} ${ticker.changePercentFormatted}")
                }
            }
        }

        builder
            .setContentTitle("📊 Crypto Market Update") // Required: contentTitle for Live Update
            .setContentText("${primaryTicker.symbol}/USD: $${String.format("%.2f", primaryTicker.currentValue)}")
            .setOnlyAlertOnce(true) // Prevent repeated pings on updates
            .setStyle(
                NotificationCompat.BigTextStyle() // Live Update compliant: BigTextStyle
                    .bigText(bigText)
                    .setBigContentTitle("💼 Fintech Mode: Live Crypto Prices")
            )
            .setColor(0xFF1976D2.toInt()) // Fintech blue
            // Note: setShortCriticalText is now set globally after style is applied
    }

    /**
     * Sports Mode: Standard style with LIVE title and scores.
     * Compliant with Live Update requirements: Ongoing, user-initiated, time-sensitive.
     */
    private fun applySportsStyle(
        builder: NotificationCompat.Builder,
        tickers: List<TickerItem>
    ) {
        val primaryTicker = tickers.firstOrNull() ?: return
        val teamScore = primaryTicker.currentValue.toInt()
        val opponentScore = (primaryTicker.currentValue / 10).toInt()
        val quarter = ((primaryTicker.currentValue.toInt() / 300) % 4) + 1
        
        val contentText = buildString {
            append("Team ${teamScore} - ${opponentScore} Opponent\n")
            append("Q$quarter • Live Match")
        }

        builder
            .setContentTitle("🔴 LIVE: Game in Progress") // Required: contentTitle for Live Update
            .setContentText(contentText)
            .setOnlyAlertOnce(true) // Prevent repeated pings on updates
            .setStyle(
                NotificationCompat.BigTextStyle() // Live Update compliant: BigTextStyle
                    .bigText("Team: $teamScore\nOpponent: $opponentScore\nQuarter: Q$quarter\n\n⚽ Live Match Updates")
                    .setBigContentTitle("🔴 LIVE: Sports Mode")
            )
            .setColor(0xFFFF6F00.toInt()) // Sports orange

        // Status Chip - Use when time for game clock countdown
        // Set when time to end of current quarter (approximately 12 minutes from game start)
        val quarterMinutes = ((primaryTicker.currentValue.toInt() % 720) / 60)
        val remainingMinutesInQuarter = (12 - quarterMinutes).coerceAtLeast(2) // At least 2 minutes
        val whenTime = System.currentTimeMillis() + (remainingMinutesInQuarter * 60 * 1000L)
        builder.setWhen(whenTime)
        // Note: setShowWhen(true) is already set globally in updateNotification
        // Use chronometer for countdown
        builder.setUsesChronometer(true)
        builder.setChronometerCountDown(true)
        // Note: setShortCriticalText is now set globally after style is applied
    }

    /**
     * Rewards Mode: ProgressStyle to show progress toward next tier.
     * Uses new Android 15+ ProgressStyle API when available.
     * Compliant with Live Update requirements: Ongoing, user-initiated, time-sensitive.
     */
    private fun applyRewardsStyle(
        builder: NotificationCompat.Builder,
        tickers: List<TickerItem>
    ) {
        val primaryTicker = tickers.firstOrNull() ?: return
        val currentPoints = primaryTicker.currentValue.toInt()
        val pointsInCurrentTier = (primaryTicker.currentValue % 1000).toInt()
        val pointsToNextTier = (1000 - pointsInCurrentTier).toInt()
        // Progress as float between 0.0 and 1.0
        val progress = pointsInCurrentTier / 1000.0
        val progressPercent = (progress * 100).toInt()

        builder
            .setContentTitle("⭐ Rewards Progress") // Required: contentTitle for Live Update
            .setContentText("$currentPoints pts • $pointsToNextTier to next tier")
            .setColor(0xFF7B1FA2.toInt()) // Rewards purple

        // Status Chip - Use when time for countdown (2+ minutes in future)
        // Estimate time to reach next tier based on current earning rate
        val estimatedMinutesToTier = (pointsToNextTier / 10).coerceAtLeast(2) // At least 2 minutes
        val whenTime = System.currentTimeMillis() + (estimatedMinutesToTier * 60 * 1000L)
        builder.setWhen(whenTime)
        builder.setShowWhen(true)

        // Use new ProgressStyle API on Android 15+ (API 35+)
        // Live Update compliant: ProgressStyle is supported
        if (Build.VERSION.SDK_INT >= 35) {
            try {
                applyRewardsProgressStyleAndroid15(builder, currentPoints, pointsToNextTier, progress)
                // Note: setShortCriticalText is now set globally after style is applied
            } catch (e: Exception) {
                // Fallback to legacy style if new API not available
                applyRewardsProgressStyleLegacy(builder, currentPoints, pointsToNextTier, progress)
            }
        } else {
            applyRewardsProgressStyleLegacy(builder, currentPoints, pointsToNextTier, progress)
        }
    }

    /**
     * Android 15+ ProgressStyle with progress points and segments.
     */
    @RequiresApi(35)
    private fun applyRewardsProgressStyleAndroid15(
        builder: NotificationCompat.Builder,
        currentPoints: Int,
        pointsToNextTier: Int,
        progress: Double
    ) {
        try {
            // Create ProgressStyle using reflection for Android 15+ APIs
            val progressStyleClass = Class.forName("androidx.core.app.NotificationCompat\$ProgressStyle")
            val progressStyleInstance = progressStyleClass.getDeclaredConstructor().newInstance()

            // Set progress points (milestones)
            val pointColor = Color.valueOf(
                236f / 255f,
                183f / 255f,
                255f / 255f,
                1f
            ).toArgb()
            val segmentColor = Color.valueOf(
                134f / 255f,
                247f / 255f,
                250f / 255f,
                1f
            ).toArgb()

            // Points at 25%, 50%, 75%, 100%
            val pointClass = Class.forName("androidx.core.app.NotificationCompat\$ProgressStyle\$Point")
            val points = listOf(
                pointClass.getConstructor(Int::class.java).newInstance(25),
                pointClass.getConstructor(Int::class.java).newInstance(50),
                pointClass.getConstructor(Int::class.java).newInstance(75),
                pointClass.getConstructor(Int::class.java).newInstance(100)
            )

            // Set colors on points
            val setColorMethod = pointClass.getMethod("setColor", Int::class.java)
            points.forEach { point ->
                setColorMethod.invoke(point, pointColor)
            }

            // Segments
            val segmentClass = Class.forName("androidx.core.app.NotificationCompat\$ProgressStyle\$Segment")
            val segments = listOf(
                segmentClass.getConstructor(Int::class.java).newInstance(25),
                segmentClass.getConstructor(Int::class.java).newInstance(25),
                segmentClass.getConstructor(Int::class.java).newInstance(25),
                segmentClass.getConstructor(Int::class.java).newInstance(25)
            )

            val setSegmentColorMethod = segmentClass.getMethod("setColor", Int::class.java)
            segments.forEach { segment ->
                setSegmentColorMethod.invoke(segment, segmentColor)
            }

            // Apply to style
            val setProgressPointsMethod = progressStyleClass.getMethod("setProgressPoints", List::class.java)
            setProgressPointsMethod.invoke(progressStyleInstance, points)

            val setProgressSegmentsMethod = progressStyleClass.getMethod("setProgressSegments", List::class.java)
            setProgressSegmentsMethod.invoke(progressStyleInstance, segments)

            // Set progress
            val setProgressMethod = progressStyleClass.getMethod("setProgress", Int::class.java, Int::class.java)
            setProgressMethod.invoke(progressStyleInstance, 100, (progress * 100).toInt())

            // Set progress tracker icon (star icon for rewards)
            try {
                val setProgressTrackerIconMethod = progressStyleClass.getMethod("setProgressTrackerIcon", IconCompat::class.java)
                // Create a star icon using system drawable converted to IconCompat
                val starIcon = IconCompat.createWithResource(
                    context,
                    android.R.drawable.star_big_on
                )
                setProgressTrackerIconMethod.invoke(progressStyleInstance, starIcon)
                android.util.Log.d("PulseNotificationManager", "Progress tracker icon (star) set successfully")
            } catch (e: Exception) {
                android.util.Log.w("PulseNotificationManager", "setProgressTrackerIcon not available", e)
                // API might not be available yet, continue without icon
            }

            // Apply style to builder
            val setStyleMethod = builder.javaClass.getMethod("setStyle", NotificationCompat.Style::class.java)
            setStyleMethod.invoke(builder, progressStyleInstance)
            // Note: setShortCriticalText is now set globally after style is applied

        } catch (e: Exception) {
            // If reflection fails, use legacy style
            applyRewardsProgressStyleLegacy(builder, currentPoints, pointsToNextTier, progress)
        }
    }

    /**
     * Legacy ProgressStyle for older Android versions.
     */
    private fun applyRewardsProgressStyleLegacy(
        builder: NotificationCompat.Builder,
        currentPoints: Int,
        pointsToNextTier: Int,
        progress: Double
    ) {
        builder
            .setProgress(100, (progress * 100).toInt(), false)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Points Earned: $currentPoints\nProgress to Next Tier: ${(progress * 100).toInt()}%\nPoints Remaining: $pointsToNextTier\n\n🎉 Keep earning to unlock rewards!")
                    .setBigContentTitle("⭐ Rewards Mode: Points Tracker")
            )
    }

    /**
     * Gets short status text for the status bar chip (max 7 characters).
     * Returns formatted text optimized for status bar display.
     * 
     * @param appMode Current app mode
     * @param tickers List of ticker items
     * @return Short status text (max 7 chars) for status bar chip
     */
    private fun getShortStatusText(appMode: AppMode, tickers: List<TickerItem>): String {
        if (tickers.isEmpty()) return ""
        
        val primaryTicker = tickers.first()
        
        return when (appMode) {
            AppMode.FINTECH_MODE -> {
                // Format: "$216.2" (live price, max 7 chars)
                val price = String.format("%.1f", primaryTicker.currentValue)
                val shortPrice = "$$price"
                if (shortPrice.length <= 7) shortPrice else "$${String.format("%.0f", primaryTicker.currentValue)}"
            }
            AppMode.SPORTS_MODE -> {
                // Format: "112-110" (live score, max 7 chars)
                val teamScore = primaryTicker.currentValue.toInt()
                val opponentScore = (primaryTicker.currentValue / 10).toInt()
                val scoreText = "$teamScore-$opponentScore"
                if (scoreText.length <= 7) scoreText else "LIVE"
            }
            AppMode.REWARDS_MODE -> {
                // Format: "126pts" (current points, max 7 chars)
                val points = primaryTicker.currentValue.toInt()
                val pointsText = "${points}pts"
                if (pointsText.length <= 7) pointsText else "${points}pt".take(7)
            }
        }
    }

    /**
     * Gets small icon for notifications.
     * Uses the app icon from the app module.
     */
    private fun getSmallIcon(appMode: AppMode): Int {
        // Use app icon (unnamed.jpg) for notification small icon
        // Priority: ic_pulse_notification_monochrome -> ic_pulse_notification -> PackageManager icon (uses monochrome layer)
        // NOTE: Small notification icons MUST be monochrome (white/transparent) for proper system tinting
        
        // Try ic_pulse_notification_monochrome first (proper monochrome version of unnamed.jpg)
        var iconId = context.resources.getIdentifier(
            "ic_pulse_notification_monochrome",
            "drawable",
            context.packageName
        )
        if (iconId != 0) {
            android.util.Log.d("PulseNotificationManager", "✅ Using ic_pulse_notification_monochrome (based on unnamed.jpg) for small icon, resource ID: $iconId")
            return iconId
        }
        
        // Fallback to ic_pulse_notification (other monochrome vector drawable)
        iconId = context.resources.getIdentifier(
            "ic_pulse_notification",
            "drawable",
            context.packageName
        )
        if (iconId != 0) {
            android.util.Log.d("PulseNotificationManager", "✅ Using ic_pulse_notification (monochrome) for small icon, resource ID: $iconId")
            return iconId
        }
        
        // Try using application icon from PackageManager (ic_launcher adaptive icon)
        // Android will extract the monochrome layer if available
        try {
            val appInfo = context.packageManager.getApplicationInfo(context.packageName, 0)
            if (appInfo.icon != 0) {
                iconId = appInfo.icon
                android.util.Log.d("PulseNotificationManager", "Using application icon from PackageManager (ic_launcher), resource ID: $iconId")
                return iconId
            }
        } catch (e: Exception) {
            android.util.Log.w("PulseNotificationManager", "Failed to get app icon from PackageManager", e)
        }
        
        // Fallback to app_icon_drawable (wraps app_icon.jpg which is unnamed.jpg)
        // WARNING: This is a full-color JPG and may not display correctly as a small icon
        iconId = context.resources.getIdentifier(
            "app_icon_drawable",
            "drawable",
            context.packageName
        )
        if (iconId != 0) {
            android.util.Log.w("PulseNotificationManager", "⚠️ Using app_icon_drawable (full-color JPG) - may not display correctly. Resource ID: $iconId")
            return iconId
        }
        
        // Fallback to direct app_icon reference (the JPG file)
        iconId = context.resources.getIdentifier(
            "app_icon",
            "drawable",
            context.packageName
        )
        if (iconId != 0) {
            android.util.Log.w("PulseNotificationManager", "⚠️ Using app_icon (full-color JPG) - may not display correctly. Resource ID: $iconId")
            return iconId
        }
        
        // If not found, log error and use system fallback
        android.util.Log.e("PulseNotificationManager", "❌ App icon not found! Package: ${context.packageName}")
        android.util.Log.e("PulseNotificationManager", "This will show Android robot icon - all icon lookups failed")
        
        // Fallback to mode-specific system icons (but user should see Android robot if this happens)
        return when (appMode) {
            AppMode.FINTECH_MODE -> android.R.drawable.ic_menu_compass
            AppMode.SPORTS_MODE -> android.R.drawable.ic_menu_view
            AppMode.REWARDS_MODE -> android.R.drawable.star_big_on
        }
    }

    /**
     * Gets large icon bitmap based on app mode.
     * Loads the full-color custom icon (unnamed.jpg) for all modes.
     * 
     * Future enhancement: Can be made dynamic per mode:
     * - FINTECH: Crypto symbol (BTC/ETH icon)
     * - SPORTS: Team logo (Lakers/Celtics logo)
     * - REWARDS: Golden star or custom "P" brand icon
     */
    private fun getLargeIcon(appMode: AppMode): android.graphics.Bitmap? {
        try {
            // Load the full-color custom app icon (unnamed.jpg) as large icon
            // Use dynamic icons based on AppMode
            val iconResId = when (appMode) {
                AppMode.FINTECH_MODE -> {
                    // Future: Load crypto symbol icon (BTC, ETH, etc.)
                    // For now, use the custom app icon
                    context.resources.getIdentifier(
                        "app_icon",
                        "drawable",
                        context.packageName
                    )
                }
                AppMode.SPORTS_MODE -> {
                    // Future: Load team logo (Lakers, Celtics, etc.)
                    // For now, use the custom app icon
                    context.resources.getIdentifier(
                        "app_icon",
                        "drawable",
                        context.packageName
                    )
                }
                AppMode.REWARDS_MODE -> {
                    // Future: Load golden star or custom "P" brand icon
                    // For now, use the custom app icon
                    context.resources.getIdentifier(
                        "app_icon",
                        "drawable",
                        context.packageName
                    )
                }
            }
            
            if (iconResId != 0) {
                // Decode the bitmap with appropriate size for large icon
                val bitmap = BitmapFactory.decodeResource(
                    context.resources,
                    iconResId,
                    android.graphics.BitmapFactory.Options().apply {
                        // Load at optimal size for large icons (256x256px minimum)
                        inJustDecodeBounds = false
                        inSampleSize = 1
                    }
                )
                
                if (bitmap != null) {
                    android.util.Log.d("PulseNotificationManager", "✅ Loaded large icon (unnamed.jpg) for $appMode, size: ${bitmap.width}x${bitmap.height}")
                    
                    // Large icons should be at least 256x256px for best quality
                    val targetSize = 256
                    return if (bitmap.width >= targetSize && bitmap.height >= targetSize) {
                        bitmap
                    } else {
                        // Scale up if needed (maintain aspect ratio)
                        val scale = targetSize.toFloat() / bitmap.width.coerceAtLeast(bitmap.height)
                        val scaledWidth = (bitmap.width * scale).toInt()
                        val scaledHeight = (bitmap.height * scale).toInt()
                        
                        android.graphics.Bitmap.createScaledBitmap(
                            bitmap,
                            scaledWidth,
                            scaledHeight,
                            true
                        ).also {
                            if (it != bitmap) {
                                bitmap.recycle() // Recycle original if we created a new one
                            }
                        }
                    }
                }
            } else {
                android.util.Log.w("PulseNotificationManager", "app_icon (unnamed.jpg) resource not found for mode: $appMode")
            }
            
        } catch (e: Exception) {
            android.util.Log.e("PulseNotificationManager", "Error loading large icon for mode: $appMode", e)
        }
        
        return null
    }

    /**
     * Creates PendingIntent for opening the app when notification is tapped.
     * Includes the current app mode as an intent extra so the activity opens in the correct mode.
     */
    private fun createPendingIntent(mainActivityClass: Class<*>, appMode: AppMode): PendingIntent {
        val intent = Intent(context, mainActivityClass).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(EXTRA_APP_MODE, appMode.name) // Pass the current mode
        }
        return PendingIntent.getActivity(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    /**
     * Creates PendingIntent for detecting when user dismisses the notification.
     * This is important for Live Updates to avoid reposting dismissed notifications.
     */
    private fun createDeleteIntent(): PendingIntent {
        val intent = Intent(context, NotificationDismissReceiver::class.java).apply {
            action = ACTION_NOTIFICATION_DISMISSED
        }
        return PendingIntent.getBroadcast(
            context,
            REQUEST_CODE + 1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    /**
     * Shows a default notification when tickers are empty.
     */
    private fun showDefaultNotification(
        currentMode: AppMode,
        mainActivityClass: Class<*>
    ) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(getSmallIcon(currentMode))
            .setOngoing(true)
            .setShowWhen(true) // Required for Promoted Ongoing
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(createPendingIntent(mainActivityClass, currentMode))
            .setColorized(false) // Required: Must NOT be colorized

        when (currentMode) {
            AppMode.FINTECH_MODE -> {
                builder.setContentTitle("📊 PulsePoint Fintech Mode") // Required: contentTitle
                    .setContentText("Waiting for market data...")
            }
            AppMode.SPORTS_MODE -> {
                builder.setContentTitle("🔴 PulsePoint Sports Mode") // Required: contentTitle
                    .setContentText("Waiting for live scores...")
            }
            AppMode.REWARDS_MODE -> {
                builder.setContentTitle("⭐ PulsePoint Rewards Mode") // Required: contentTitle
                    .setContentText("Waiting for points data...")
            }
        }

        // Android 16 (API 36+): Request promoted ongoing notification
        if (Build.VERSION.SDK_INT >= 36) {
            try {
                val method = builder.javaClass.getMethod("setRequestPromotedOngoing", Boolean::class.java)
                method.invoke(builder, true)
            } catch (e: Exception) {
                // API not available
            }
        }

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    /**
     * Cancels the ongoing notification.
     */
    fun cancelNotification() {
        notificationManager.cancel(NOTIFICATION_ID)
    }

    /**
     * Checks if promoted notifications are enabled (Android 15+).
     */
    fun canPostPromotedNotifications(): Boolean {
        return if (Build.VERSION.SDK_INT >= 35) {
            try {
                val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val method = manager.javaClass.getMethod("canPostPromotedNotifications")
                method.invoke(manager) as Boolean
            } catch (e: Exception) {
                true // Default to true if API not available
            }
        } else {
            true // Always true on older versions
        }
    }
}
