package com.example.pulsepoint.feature.dashboard.model

/**
 * Application configuration modes that change the app's visual skin.
 * Each mode represents a different domain (Sports, Rewards, Fintech)
 * with potentially different data interpretation and UI styling.
 */
enum class AppConfig {
    SPORTS_MODE,    // Team Scores & Odds
    REWARDS_MODE,   // Points & Bonus Multipliers
    FINTECH_MODE    // Crypto Prices & Volatility
}
