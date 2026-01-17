package com.example.pulsepoint.feature.dashboard.ui;

/**
 * MVI UiState for the Dashboard screen.
 * Represents all possible states the UI can be in.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState;", "", "Error", "Loading", "Success", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState$Error;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState$Loading;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState$Success;", "dashboard_debug"})
public abstract interface DashboardUiState {
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState$Error;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "dashboard_debug"})
    public static final class Error implements com.example.pulsepoint.feature.dashboard.ui.DashboardUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String message = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.pulsepoint.feature.dashboard.ui.DashboardUiState.Error copy(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
        
        public Error(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMessage() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState$Loading;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "dashboard_debug"})
    public static final class Loading implements com.example.pulsepoint.feature.dashboard.ui.DashboardUiState {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.pulsepoint.feature.dashboard.ui.DashboardUiState.Loading INSTANCE = null;
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
        
        private Loading() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState$Success;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiState;", "tickers", "", "Lcom/example/pulsepoint/core/network/model/TickerItem;", "currentMode", "Lcom/example/pulsepoint/feature/dashboard/model/AppConfig;", "(Ljava/util/List;Lcom/example/pulsepoint/feature/dashboard/model/AppConfig;)V", "getCurrentMode", "()Lcom/example/pulsepoint/feature/dashboard/model/AppConfig;", "getTickers", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "dashboard_debug"})
    public static final class Success implements com.example.pulsepoint.feature.dashboard.ui.DashboardUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.example.pulsepoint.core.network.model.TickerItem> tickers = null;
        @org.jetbrains.annotations.NotNull()
        private final com.example.pulsepoint.feature.dashboard.model.AppConfig currentMode = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.example.pulsepoint.core.network.model.TickerItem> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.pulsepoint.feature.dashboard.model.AppConfig component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.pulsepoint.feature.dashboard.ui.DashboardUiState.Success copy(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.pulsepoint.core.network.model.TickerItem> tickers, @org.jetbrains.annotations.NotNull()
        com.example.pulsepoint.feature.dashboard.model.AppConfig currentMode) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
        
        public Success(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.pulsepoint.core.network.model.TickerItem> tickers, @org.jetbrains.annotations.NotNull()
        com.example.pulsepoint.feature.dashboard.model.AppConfig currentMode) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.example.pulsepoint.core.network.model.TickerItem> getTickers() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.pulsepoint.feature.dashboard.model.AppConfig getCurrentMode() {
            return null;
        }
    }
}