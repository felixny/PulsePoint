package com.example.pulsepoint.feature.dashboard.ui;

/**
 * ViewModel implementing strict MVI (Model-View-Intent) pattern.
 * Exposes a single UiState and handles UiIntent events.
 * Integrates with LiveUpdateService for real-time notifications.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\rH\u0002J\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u0015H\u0002J\b\u0010\u001c\u001a\u00020\u0015H\u0014J\b\u0010\u001d\u001a\u00020\u0015H\u0002R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011\u00a8\u0006\u001e"}, d2 = {"Lcom/example/pulsepoint/feature/dashboard/ui/DashboardViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "tickerRepository", "Lcom/example/pulsepoint/core/data/repository/TickerRepository;", "(Landroid/app/Application;Lcom/example/pulsepoint/core/data/repository/TickerRepository;)V", "_uiEffect", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiEffect;", "_uiState", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState;", "currentMode", "Lcom/example/pulsepoint/feature/dashboard/model/AppConfig;", "uiEffect", "Lkotlinx/coroutines/flow/StateFlow;", "getUiEffect", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "changeMode", "", "mode", "clearEffect", "handleIntent", "intent", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent;", "loadTickers", "onCleared", "toggleSampling", "dashboard_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DashboardViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.pulsepoint.core.data.repository.TickerRepository tickerRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.pulsepoint.feature.dashboard.ui.DashboardUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.pulsepoint.feature.dashboard.ui.DashboardUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.pulsepoint.feature.dashboard.ui.DashboardUiEffect> _uiEffect = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.pulsepoint.feature.dashboard.ui.DashboardUiEffect> uiEffect = null;
    @org.jetbrains.annotations.NotNull()
    private com.example.pulsepoint.feature.dashboard.model.AppConfig currentMode = com.example.pulsepoint.feature.dashboard.model.AppConfig.FINTECH_MODE;
    
    @javax.inject.Inject()
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.example.pulsepoint.core.data.repository.TickerRepository tickerRepository) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.pulsepoint.feature.dashboard.ui.DashboardUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.pulsepoint.feature.dashboard.ui.DashboardUiEffect> getUiEffect() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    /**
     * Single entry point for handling all user intents.
     * This is the core of MVI pattern.
     */
    public final void handleIntent(@org.jetbrains.annotations.NotNull()
    com.example.pulsepoint.feature.dashboard.ui.DashboardUiIntent intent) {
    }
    
    private final void loadTickers() {
    }
    
    private final void changeMode(com.example.pulsepoint.feature.dashboard.model.AppConfig mode) {
    }
    
    private final void toggleSampling() {
    }
    
    /**
     * Clears the current UI effect after it has been consumed.
     */
    public final void clearEffect() {
    }
}