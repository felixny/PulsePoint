package com.example.pulsepoint.core.notifications;

/**
 * Notification Manager for PulsePoint Live Updates.
 * Handles industry-specific notification styles with dynamic theming.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J(\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0003J(\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001e\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u001e\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\bJ\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\bH\u0002J\u001c\u0010\u001d\u001a\u00020\u001b2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0012\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010 \u001a\u00020!H\u0002J\u001e\u0010$\u001a\u00020%2\u0006\u0010 \u001a\u00020!2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0010\u0010&\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!H\u0002J\u001c\u0010\'\u001a\u00020\b2\u0006\u0010(\u001a\u00020!2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001fH\u0002J(\u0010)\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010(\u001a\u00020!2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/example/pulsepoint/core/notifications/PulseNotificationManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "applyFintechStyle", "", "builder", "Landroidx/core/app/NotificationCompat$Builder;", "tickers", "", "Lcom/example/pulsepoint/core/network/model/TickerItem;", "applyRewardsProgressStyleAndroid15", "currentPoints", "", "pointsToNextTier", "progress", "", "applyRewardsProgressStyleLegacy", "applyRewardsStyle", "applySportsStyle", "canPostPromotedNotifications", "", "cancelNotification", "createDeleteIntent", "Landroid/app/PendingIntent;", "createNotificationChannel", "createPendingIntent", "mainActivityClass", "Ljava/lang/Class;", "appMode", "Lcom/example/pulsepoint/core/ui/theme/AppMode;", "getLargeIcon", "Landroid/graphics/Bitmap;", "getShortStatusText", "", "getSmallIcon", "showDefaultNotification", "currentMode", "updateNotification", "Companion", "notifications_debug"})
public final class PulseNotificationManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.core.app.NotificationManagerCompat notificationManager = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID = "live_updates_channel";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_NAME = "Live Updates";
    public static final int NOTIFICATION_ID = 1001;
    private static final int REQUEST_CODE = 100;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_NOTIFICATION_DISMISSED = "com.example.pulsepoint.NOTIFICATION_DISMISSED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_APP_MODE = "com.example.pulsepoint.EXTRA_APP_MODE";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.pulsepoint.core.notifications.PulseNotificationManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public PulseNotificationManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Creates a high-priority notification channel for live updates.
     */
    private final void createNotificationChannel() {
    }
    
    /**
     * Shows or updates the notification based on the current mode and ticker data.
     *
     * @param tickers List of current ticker items
     * @param currentMode Current app mode (Fintech, Sports, Rewards)
     * @param mainActivityClass The main activity class for the PendingIntent
     */
    public final void updateNotification(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.pulsepoint.core.network.model.TickerItem> tickers, @org.jetbrains.annotations.NotNull()
    com.example.pulsepoint.core.ui.theme.AppMode currentMode, @org.jetbrains.annotations.NotNull()
    java.lang.Class<?> mainActivityClass) {
    }
    
    /**
     * Fintech Mode: BigTextStyle for crypto price and volatility.
     * Compliant with Live Update requirements: Ongoing, user-initiated, time-sensitive.
     */
    private final void applyFintechStyle(androidx.core.app.NotificationCompat.Builder builder, java.util.List<com.example.pulsepoint.core.network.model.TickerItem> tickers) {
    }
    
    /**
     * Sports Mode: Standard style with LIVE title and scores.
     * Compliant with Live Update requirements: Ongoing, user-initiated, time-sensitive.
     */
    private final void applySportsStyle(androidx.core.app.NotificationCompat.Builder builder, java.util.List<com.example.pulsepoint.core.network.model.TickerItem> tickers) {
    }
    
    /**
     * Rewards Mode: ProgressStyle to show progress toward next tier.
     * Uses new Android 15+ ProgressStyle API when available.
     * Compliant with Live Update requirements: Ongoing, user-initiated, time-sensitive.
     */
    private final void applyRewardsStyle(androidx.core.app.NotificationCompat.Builder builder, java.util.List<com.example.pulsepoint.core.network.model.TickerItem> tickers) {
    }
    
    /**
     * Android 15+ ProgressStyle with progress points and segments.
     */
    @androidx.annotation.RequiresApi(value = 35)
    private final void applyRewardsProgressStyleAndroid15(androidx.core.app.NotificationCompat.Builder builder, int currentPoints, int pointsToNextTier, double progress) {
    }
    
    /**
     * Legacy ProgressStyle for older Android versions.
     */
    private final void applyRewardsProgressStyleLegacy(androidx.core.app.NotificationCompat.Builder builder, int currentPoints, int pointsToNextTier, double progress) {
    }
    
    /**
     * Gets short status text for the status bar chip (max 7 characters).
     * Returns formatted text optimized for status bar display.
     *
     * @param appMode Current app mode
     * @param tickers List of ticker items
     * @return Short status text (max 7 chars) for status bar chip
     */
    private final java.lang.String getShortStatusText(com.example.pulsepoint.core.ui.theme.AppMode appMode, java.util.List<com.example.pulsepoint.core.network.model.TickerItem> tickers) {
        return null;
    }
    
    /**
     * Gets small icon for notifications.
     * Uses the app icon from the app module.
     */
    private final int getSmallIcon(com.example.pulsepoint.core.ui.theme.AppMode appMode) {
        return 0;
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
    private final android.graphics.Bitmap getLargeIcon(com.example.pulsepoint.core.ui.theme.AppMode appMode) {
        return null;
    }
    
    /**
     * Creates PendingIntent for opening the app when notification is tapped.
     * Includes the current app mode as an intent extra so the activity opens in the correct mode.
     */
    private final android.app.PendingIntent createPendingIntent(java.lang.Class<?> mainActivityClass, com.example.pulsepoint.core.ui.theme.AppMode appMode) {
        return null;
    }
    
    /**
     * Creates PendingIntent for detecting when user dismisses the notification.
     * This is important for Live Updates to avoid reposting dismissed notifications.
     */
    private final android.app.PendingIntent createDeleteIntent() {
        return null;
    }
    
    /**
     * Shows a default notification when tickers are empty.
     */
    private final void showDefaultNotification(com.example.pulsepoint.core.ui.theme.AppMode currentMode, java.lang.Class<?> mainActivityClass) {
    }
    
    /**
     * Cancels the ongoing notification.
     */
    public final void cancelNotification() {
    }
    
    /**
     * Checks if promoted notifications are enabled (Android 15+).
     */
    public final boolean canPostPromotedNotifications() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/pulsepoint/core/notifications/PulseNotificationManager$Companion;", "", "()V", "ACTION_NOTIFICATION_DISMISSED", "", "CHANNEL_ID", "CHANNEL_NAME", "EXTRA_APP_MODE", "NOTIFICATION_ID", "", "REQUEST_CODE", "notifications_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}