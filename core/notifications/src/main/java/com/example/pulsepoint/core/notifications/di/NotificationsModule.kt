package com.example.pulsepoint.core.notifications.di

import android.content.Context
import com.example.pulsepoint.core.notifications.PulseNotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for notification dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object NotificationsModule {

    @Provides
    @Singleton
    fun providePulseNotificationManager(
        @ApplicationContext context: Context
    ): PulseNotificationManager {
        return PulseNotificationManager(context)
    }
}
