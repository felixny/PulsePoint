package com.example.pulsepoint.feature.dashboard.ui

import com.example.pulsepoint.core.network.model.TickerItem
import com.example.pulsepoint.feature.dashboard.model.AppConfig

/**
 * MVI UiState for the Dashboard screen.
 * Represents all possible states the UI can be in.
 */
sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(
        val tickers: List<TickerItem>,
        val currentMode: AppConfig
    ) : DashboardUiState
    data class Error(
        val message: String
    ) : DashboardUiState
}

/**
 * MVI UiIntent - represents user actions/events.
 */
sealed interface DashboardUiIntent {
    data object LoadTickers : DashboardUiIntent
    data class ChangeMode(val mode: AppConfig) : DashboardUiIntent
    data object ToggleSampling : DashboardUiIntent
}

/**
 * MVI UiEffect - represents side effects (one-time events like navigation, toasts).
 */
sealed interface DashboardUiEffect {
    data class ShowError(val message: String) : DashboardUiEffect
    data class ShowMessage(val message: String) : DashboardUiEffect
}
