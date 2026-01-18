package com.example.pulsepoint.feature.dashboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pulsepoint.core.ui.theme.PulsePointTheme
import com.example.pulsepoint.feature.dashboard.model.AppConfig
import com.example.pulsepoint.feature.dashboard.model.toAppMode

/**
 * Dashboard screen with MVI architecture.
 * Shows ticker items in a LazyColumn with animated value changes.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    initialMode: AppConfig? = null
) {
    val uiState by viewModel.uiState.collectAsState()
    val uiEffect by viewModel.uiEffect.collectAsState()

    // Set initial mode from intent if provided (when opened from notification)
    LaunchedEffect(initialMode) {
        if (initialMode != null) {
            viewModel.handleIntent(DashboardUiIntent.ChangeMode(initialMode))
        }
    }

    // Get current mode for theme
    val currentMode = (uiState as? DashboardUiState.Success)?.currentMode ?: (initialMode ?: AppConfig.FINTECH_MODE)
    val appMode = currentMode.toAppMode()

    // Handle UI effects (toasts, navigation, etc.)
    LaunchedEffect(uiEffect) {
        uiEffect?.let { effect ->
            when (effect) {
                is DashboardUiEffect.ShowError -> {
                    // TODO: Show error toast/snackbar
                }

                is DashboardUiEffect.ShowMessage -> {
                    // TODO: Show message toast/snackbar
                }
            }
            viewModel.clearEffect()
        }
    }

    // Wrap entire screen in dynamic theme
    PulsePointTheme(currentMode = appMode) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("PulsePoint Dashboard") }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Mode Toggle Section
                ModeToggleSection(
                    currentMode = currentMode,
                    onModeChanged = { mode ->
                        viewModel.handleIntent(DashboardUiIntent.ChangeMode(mode))
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                // Main Content
                when (uiState) {
                    is DashboardUiState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is DashboardUiState.Success -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(
                                items = (uiState as DashboardUiState.Success).tickers,
                                key = { it.id }
                            ) { ticker ->
                                AnimatedTickerCard(
                                    ticker = ticker,
                                    currentMode = currentMode,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                    is DashboardUiState.Error -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Error: ${(uiState as DashboardUiState.Error).message}",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Mode toggle section for switching between Sports, Rewards, and Fintech modes.
 * The 'Min-Max' toggle feature.
 */
@Composable
private fun ModeToggleSection(
    currentMode: AppConfig,
    onModeChanged: (AppConfig) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "App Mode",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            AppConfig.values().forEach { mode ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable(
                            value = currentMode == mode,
                            onValueChange = { if (it) onModeChanged(mode) },
                            role = Role.RadioButton
                        )
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = currentMode == mode,
                        onClick = { onModeChanged(mode) }
                    )
                    Text(
                        text = when (mode) {
                            AppConfig.SPORTS_MODE -> "Sports Mode (Team Scores & Odds)"
                            AppConfig.REWARDS_MODE -> "Rewards Mode (Points & Multipliers)"
                            AppConfig.FINTECH_MODE -> "Fintech Mode (Crypto & Volatility)"
                        },
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
