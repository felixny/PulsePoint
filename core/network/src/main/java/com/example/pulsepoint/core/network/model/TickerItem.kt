package com.example.pulsepoint.core.network.model

import kotlinx.serialization.Serializable

/**
 * Represents a single ticker item with price data.
 * Used across different app modes (SPORTS_MODE, REWARDS_MODE, FINTECH_MODE).
 */
@Serializable
data class TickerItem(
    val id: String,
    val symbol: String,
    val displayName: String,
    val currentValue: Double,
    val previousValue: Double,
    val change: Double,
    val changePercent: Double,
    val volume: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val history: List<Double> = emptyList()
) {
    val isPositiveChange: Boolean
        get() = change >= 0

    val changePercentFormatted: String
        get() = String.format("%.2f%%", changePercent)
}
