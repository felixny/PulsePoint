package com.example.pulsepoint.core.network.di

/**
 * Hilt module for providing network-related dependencies.
 * 
 * Note: FakeTickerProvider uses constructor injection with @Inject,
 * so Hilt will automatically provide it. This module is kept for
 * future extensibility and documentation purposes.
 */
// The module can be removed since FakeTickerProvider uses constructor injection
// But we keep it here as a placeholder for future network layer dependencies
// @Module
// @InstallIn(SingletonComponent::class)
// object NetworkModule
