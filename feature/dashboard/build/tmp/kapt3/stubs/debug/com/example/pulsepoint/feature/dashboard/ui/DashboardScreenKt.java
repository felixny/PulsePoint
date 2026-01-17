package com.example.pulsepoint.feature.dashboard.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a.\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\n2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0003\u00a8\u0006\u000b"}, d2 = {"DashboardScreen", "", "viewModel", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardViewModel;", "modifier", "Landroidx/compose/ui/Modifier;", "ModeToggleSection", "currentMode", "Lcom/example/pulsepoint/feature/dashboard/model/AppConfig;", "onModeChanged", "Lkotlin/Function1;", "dashboard_debug"})
public final class DashboardScreenKt {
    
    /**
     * Dashboard screen with MVI architecture.
     * Shows ticker items in a LazyColumn with animated value changes.
     */
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    com.example.pulsepoint.feature.dashboard.ui.DashboardViewModel viewModel, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Mode toggle section for switching between Sports, Rewards, and Fintech modes.
     * The 'Min-Max' toggle feature.
     */
    @androidx.compose.runtime.Composable()
    private static final void ModeToggleSection(com.example.pulsepoint.feature.dashboard.model.AppConfig currentMode, kotlin.jvm.functions.Function1<? super com.example.pulsepoint.feature.dashboard.model.AppConfig, kotlin.Unit> onModeChanged, androidx.compose.ui.Modifier modifier) {
    }
}