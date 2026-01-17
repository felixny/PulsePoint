package com.example.pulsepoint.feature.dashboard.model

import com.example.pulsepoint.core.ui.theme.AppMode

/**
 * Extension to convert feature AppConfig to core UI AppMode for theming.
 */
fun AppConfig.toAppMode(): AppMode {
    return when (this) {
        AppConfig.FINTECH_MODE -> AppMode.FINTECH_MODE
        AppConfig.SPORTS_MODE -> AppMode.SPORTS_MODE
        AppConfig.REWARDS_MODE -> AppMode.REWARDS_MODE
    }
}
