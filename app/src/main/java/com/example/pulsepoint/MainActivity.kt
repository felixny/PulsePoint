package com.example.pulsepoint

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.pulsepoint.core.notifications.PulseNotificationManager
import com.example.pulsepoint.core.ui.theme.AppMode
import com.example.pulsepoint.feature.dashboard.model.AppConfig
import com.example.pulsepoint.feature.dashboard.ui.DashboardScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("MainActivity", "Notification permission granted")
        } else {
            Log.w("MainActivity", "Notification permission denied - foreground service notifications may still work")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Request notification permission for Android 13+ (API 33+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("MainActivity", "Requesting POST_NOTIFICATIONS permission")
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                Log.d("MainActivity", "POST_NOTIFICATIONS permission already granted")
            }
        }
        
        // Read app mode from intent if opened from notification
        val modeFromIntent = intent?.getStringExtra(PulseNotificationManager.EXTRA_APP_MODE)?.let { modeName ->
            try {
                val appMode = AppMode.valueOf(modeName)
                // Convert AppMode to AppConfig
                when (appMode) {
                    AppMode.FINTECH_MODE -> AppConfig.FINTECH_MODE
                    AppMode.SPORTS_MODE -> AppConfig.SPORTS_MODE
                    AppMode.REWARDS_MODE -> AppConfig.REWARDS_MODE
                }
            } catch (e: Exception) {
                Log.w("MainActivity", "Invalid app mode from intent: $modeName", e)
                null
            }
        }
        
        setContent {
            // DashboardScreen handles its own theme wrapping with dynamic AppMode
            DashboardScreen(initialMode = modeFromIntent)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("Android")
}