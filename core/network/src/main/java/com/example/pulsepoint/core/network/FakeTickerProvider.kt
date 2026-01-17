package com.example.pulsepoint.core.network

import com.example.pulsepoint.core.network.model.TickerItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import javax.inject.Inject
import javax.inject.Singleton

/**
 * High-frequency ticker provider that simulates real-time data streaming.
 * Emits a Flow<List<TickerItem>> every 300ms with randomized price movements.
 * 
 * This is designed to test the app's performance at 60 FPS with rapid data updates.
 */
@Singleton
class FakeTickerProvider @Inject constructor() {

    private val random = Random.Default
    
    // Initial state - can be seeded with different data based on AppConfig mode
    private var tickerItems: List<TickerItem> = initializeTickerItems()
    
    // Rolling window size for history data (for Sparkline charts)
    private val HISTORY_WINDOW_SIZE = 15

    /**
     * Returns a Flow that emits ticker data every 300ms (approximately 3.33 updates per second).
     * This creates a high-frequency data stream to stress-test the UI's ability to maintain 60 FPS.
     */
    fun observeTickers(): Flow<List<TickerItem>> = flow {
        while (true) {
            emit(tickerItems)
            delay(300) // 300ms = ~3.33 Hz update rate
            tickerItems = tickerItems.map { updateTickerItem(it) }
        }
    }

    /**
     * Updates a single ticker item with simulated price movement.
     * Uses random walk with mean reversion to create realistic price behavior.
     * Maintains a rolling window of the last 15 values for Sparkline charts.
     */
    private fun updateTickerItem(item: TickerItem): TickerItem {
        // Random walk: price change between -2% and +2%
        val priceChangePercent = random.nextDouble(-0.02, 0.02)
        val newValue = item.currentValue * (1 + priceChangePercent)
        
        // Ensure price doesn't go negative
        val clampedValue = maxOf(0.01, newValue)
        
        val change = clampedValue - item.currentValue
        val changePercent = if (item.currentValue != 0.0) {
            (change / item.currentValue) * 100
        } else {
            0.0
        }

        // Maintain rolling window of last 15 values for Sparkline
        val updatedHistory = (item.history + item.currentValue)
            .takeLast(HISTORY_WINDOW_SIZE)

        return item.copy(
            currentValue = clampedValue,
            previousValue = item.currentValue,
            change = change,
            changePercent = changePercent,
            volume = item.volume + random.nextLong(1000, 10000),
            timestamp = System.currentTimeMillis(),
            history = updatedHistory
        )
    }

    /**
     * Initializes ticker items. Can be customized based on AppConfig mode.
     * For now, creates a generic set of tickers that can represent:
     * - SPORTS_MODE: Team scores, odds
     * - REWARDS_MODE: Points, multipliers
     * - FINTECH_MODE: Crypto prices, stock prices
     */
    private fun initializeTickerItems(): List<TickerItem> {
        val symbols = listOf(
            "BTC", "ETH", "SOL", "BNB", "ADA",
            "XRP", "DOT", "MATIC", "AVAX", "LINK",
            "LTC", "BCH", "UNI", "ATOM", "VET",
            "FIL", "TRX", "ETC", "XLM", "ALGO"
        )

        return symbols.mapIndexed { index, symbol ->
            val basePrice = 100.0 + (index * 50.0) + random.nextDouble(0.0, 200.0)
            // Initialize with history containing the base price to seed the Sparkline
            val initialHistory = List(HISTORY_WINDOW_SIZE) { basePrice }
            TickerItem(
                id = symbol.lowercase(),
                symbol = symbol,
                displayName = symbol,
                currentValue = basePrice,
                previousValue = basePrice,
                change = 0.0,
                changePercent = 0.0,
                volume = random.nextLong(100000, 1000000),
                history = initialHistory
            )
        }
    }

    /**
     * Resets the ticker provider with new initial data.
     * Useful for switching between different app modes.
     */
    fun reset() {
        tickerItems = initializeTickerItems()
    }
}
