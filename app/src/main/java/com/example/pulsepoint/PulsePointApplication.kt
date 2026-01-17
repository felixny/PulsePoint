package com.example.pulsepoint

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for PulsePoint.
 * Required for Hilt dependency injection to work.
 */
@HiltAndroidApp
class PulsePointApplication : Application()
