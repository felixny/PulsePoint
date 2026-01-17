package com.example.pulsepoint.core.data.di

/**
 * Hilt module for providing data layer dependencies.
 * 
 * Note: TickerRepository uses constructor injection with @Inject,
 * so Hilt will automatically provide it. This module is kept for
 * future extensibility and documentation purposes.
 */
// The module can be removed since TickerRepository uses constructor injection
// But we keep it here as a placeholder for future data layer dependencies
// @Module
// @InstallIn(SingletonComponent::class)
// object DataModule
