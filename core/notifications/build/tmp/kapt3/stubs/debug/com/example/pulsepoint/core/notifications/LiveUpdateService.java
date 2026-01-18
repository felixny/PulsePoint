package com.example.pulsepoint.core.notifications;

/**
 * Foreground Service for Live Update Notifications.
 * Synchronizes with TickerRepository to show real-time updates.
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u001bH\u0016J\b\u0010!\u001a\u00020\u001bH\u0016J\"\u0010\"\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u0019H\u0016J\u000e\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u0004J\b\u0010\'\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006)"}, d2 = {"Lcom/example/pulsepoint/core/notifications/LiveUpdateService;", "Landroid/app/Service;", "()V", "currentMode", "Lcom/example/pulsepoint/core/ui/theme/AppMode;", "currentTickers", "", "Lcom/example/pulsepoint/core/network/model/TickerItem;", "notificationManager", "Lcom/example/pulsepoint/core/notifications/PulseNotificationManager;", "getNotificationManager", "()Lcom/example/pulsepoint/core/notifications/PulseNotificationManager;", "setNotificationManager", "(Lcom/example/pulsepoint/core/notifications/PulseNotificationManager;)V", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "tickerRepository", "Lcom/example/pulsepoint/core/data/repository/TickerRepository;", "getTickerRepository", "()Lcom/example/pulsepoint/core/data/repository/TickerRepository;", "setTickerRepository", "(Lcom/example/pulsepoint/core/data/repository/TickerRepository;)V", "createInitialNotification", "Landroid/app/Notification;", "getAppIcon", "", "observeTickers", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "updateMode", "mode", "updateNotification", "Companion", "notifications_debug"})
public final class LiveUpdateService extends android.app.Service {
    @javax.inject.Inject()
    public com.example.pulsepoint.core.data.repository.TickerRepository tickerRepository;
    @javax.inject.Inject()
    public com.example.pulsepoint.core.notifications.PulseNotificationManager notificationManager;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    @org.jetbrains.annotations.NotNull()
    private com.example.pulsepoint.core.ui.theme.AppMode currentMode = com.example.pulsepoint.core.ui.theme.AppMode.FINTECH_MODE;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.pulsepoint.core.network.model.TickerItem> currentTickers;
    private static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_MODE = "extra_mode";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.pulsepoint.core.notifications.LiveUpdateService.Companion Companion = null;
    
    public LiveUpdateService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.pulsepoint.core.data.repository.TickerRepository getTickerRepository() {
        return null;
    }
    
    public final void setTickerRepository(@org.jetbrains.annotations.NotNull()
    com.example.pulsepoint.core.data.repository.TickerRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.pulsepoint.core.notifications.PulseNotificationManager getNotificationManager() {
        return null;
    }
    
    public final void setNotificationManager(@org.jetbrains.annotations.NotNull()
    com.example.pulsepoint.core.notifications.PulseNotificationManager p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    /**
     * Observes ticker updates from the repository.
     * Debounces notification updates to prevent excessive updates (max once per second).
     */
    private final void observeTickers() {
    }
    
    /**
     * Updates the notification with latest ticker data.
     */
    private final void updateNotification() {
    }
    
    /**
     * Creates initial notification to start as foreground service.
     */
    private final android.app.Notification createInitialNotification() {
        return null;
    }
    
    /**
     * Gets the app icon for notifications.
     * Uses the app icon from the app module.
     */
    private final int getAppIcon() {
        return 0;
    }
    
    /**
     * Updates the current mode and refreshes notification.
     */
    public final void updateMode(@org.jetbrains.annotations.NotNull()
    com.example.pulsepoint.core.ui.theme.AppMode mode) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/example/pulsepoint/core/notifications/LiveUpdateService$Companion;", "", "()V", "EXTRA_MODE", "", "NOTIFICATION_ID", "", "start", "", "context", "Landroid/content/Context;", "mode", "Lcom/example/pulsepoint/core/ui/theme/AppMode;", "stop", "notifications_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Starts the LiveUpdateService.
         */
        public final void start(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.Nullable()
        com.example.pulsepoint.core.ui.theme.AppMode mode) {
        }
        
        /**
         * Stops the LiveUpdateService.
         */
        public final void stop(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
    }
}