package com.example.pulsepoint.core.data.repository

import com.example.pulsepoint.core.network.FakeTickerProvider
import com.example.pulsepoint.core.network.model.TickerItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.sample
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing ticker data with performance optimization options.
 * 
 * Critical Feature: Provides a toggle to sample the high-frequency data stream
 * using flow.sample(500ms) to demonstrate CPU/Memory management skills.
 */
@Singleton
class TickerRepository @Inject constructor(
    private val tickerProvider: FakeTickerProvider
) {
    /**
     * Controls whether to apply sampling to reduce data frequency.
     * When enabled, samples the flow at 500ms intervals instead of raw 300ms updates.
     * This demonstrates performance optimization by reducing recompositions.
     */
    var useSampling: Boolean = false
        private set

    /**
     * Observes ticker data from the provider.
     * When sampling is enabled, reduces update frequency from 300ms to 500ms.
     * This is a critical performance feature to manage CPU/Memory during high-frequency updates.
     */
    fun observeTickers(): Flow<List<TickerItem>> {
        val rawFlow = tickerProvider.observeTickers()
        
        return if (useSampling) {
            // Sample the flow at 500ms intervals to reduce UI recompositions
            // This demonstrates performance optimization for high-frequency data streams
            rawFlow.sample(500)
        } else {
            // Raw flow collection - full 300ms update rate (~3.33 Hz)
            rawFlow
        }
    }

    /**
     * Toggles the sampling feature on/off.
     * This allows dynamic switching between raw high-frequency updates and sampled updates.
     */
    fun toggleSampling() {
        useSampling = !useSampling
    }

    /**
     * Sets the sampling state explicitly.
     */
    fun setSampling(enabled: Boolean) {
        useSampling = enabled
    }

    /**
     * Returns the current sampling state.
     */
    fun isSamplingEnabled(): Boolean = useSampling
}
