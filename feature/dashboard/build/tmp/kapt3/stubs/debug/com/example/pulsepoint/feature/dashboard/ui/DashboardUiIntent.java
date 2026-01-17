package com.example.pulsepoint.feature.dashboard.ui;

/**
 * MVI UiIntent - represents user actions/events.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent;", "", "ChangeMode", "LoadTickers", "ToggleSampling", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent$ChangeMode;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent$LoadTickers;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent$ToggleSampling;", "dashboard_debug"})
public abstract interface DashboardUiIntent {
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent$ChangeMode;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent;", "mode", "Lcom/example/pulsepoint/feature/dashboard/model/AppConfig;", "(Lcom/example/pulsepoint/feature/dashboard/model/AppConfig;)V", "getMode", "()Lcom/example/pulsepoint/feature/dashboard/model/AppConfig;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "dashboard_debug"})
    public static final class ChangeMode implements com.example.pulsepoint.feature.dashboard.ui.DashboardUiIntent {
        @org.jetbrains.annotations.NotNull()
        private final com.example.pulsepoint.feature.dashboard.model.AppConfig mode = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.pulsepoint.feature.dashboard.model.AppConfig component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.pulsepoint.feature.dashboard.ui.DashboardUiIntent.ChangeMode copy(@org.jetbrains.annotations.NotNull()
        com.example.pulsepoint.feature.dashboard.model.AppConfig mode) {
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
        
        public ChangeMode(@org.jetbrains.annotations.NotNull()
        com.example.pulsepoint.feature.dashboard.model.AppConfig mode) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.pulsepoint.feature.dashboard.model.AppConfig getMode() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent$LoadTickers;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "dashboard_debug"})
    public static final class LoadTickers implements com.example.pulsepoint.feature.dashboard.ui.DashboardUiIntent {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.pulsepoint.feature.dashboard.ui.DashboardUiIntent.LoadTickers INSTANCE = null;
        
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
        
        private LoadTickers() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent$ToggleSampling;", "Lcom/example/pulsepoint/feature/dashboard/ui/DashboardUiIntent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "dashboard_debug"})
    public static final class ToggleSampling implements com.example.pulsepoint.feature.dashboard.ui.DashboardUiIntent {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.pulsepoint.feature.dashboard.ui.DashboardUiIntent.ToggleSampling INSTANCE = null;
        
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
        
        private ToggleSampling() {
            super();
        }
    }
}