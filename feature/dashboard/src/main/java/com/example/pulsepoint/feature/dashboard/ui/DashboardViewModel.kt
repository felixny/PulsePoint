package com.example.pulsepoint.feature.dashboard.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pulsepoint.core.data.repository.TickerRepository
import com.example.pulsepoint.core.notifications.LiveUpdateService
import com.example.pulsepoint.core.ui.theme.AppMode
import com.example.pulsepoint.feature.dashboard.model.AppConfig
import com.example.pulsepoint.feature.dashboard.model.toAppMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel implementing strict MVI (Model-View-Intent) pattern.
 * Exposes a single UiState and handles UiIntent events.
 * Integrates with LiveUpdateService for real-time notifications.
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    private val tickerRepository: TickerRepository
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableStateFlow<DashboardUiEffect?>(null)
    val uiEffect: StateFlow<DashboardUiEffect?> = _uiEffect.asStateFlow()

    private var currentMode: AppConfig = AppConfig.FINTECH_MODE

    init {
        handleIntent(DashboardUiIntent.LoadTickers)
        // Start the live update service with initial mode
        LiveUpdateService.start(getApplication(), currentMode.toAppMode())
    }

    override fun onCleared() {
        super.onCleared()
        // Stop the live update service when ViewModel is cleared
        LiveUpdateService.stop(getApplication())
    }

    /**
     * Single entry point for handling all user intents.
     * This is the core of MVI pattern.
     */
    fun handleIntent(intent: DashboardUiIntent) {
        when (intent) {
            is DashboardUiIntent.LoadTickers -> loadTickers()
            is DashboardUiIntent.ChangeMode -> changeMode(intent.mode)
            is DashboardUiIntent.ToggleSampling -> toggleSampling()
        }
    }

    private fun loadTickers() {
        _uiState.value = DashboardUiState.Loading

        tickerRepository.observeTickers()
            .onEach { tickers ->
                _uiState.value = DashboardUiState.Success(
                    tickers = tickers,
                    currentMode = currentMode
                )
            }
            .catch { exception ->
                _uiState.value = DashboardUiState.Error(
                    message = exception.message ?: "Unknown error occurred"
                )
                _uiEffect.value = DashboardUiEffect.ShowError(
                    exception.message ?: "Failed to load tickers"
                )
            }
            .launchIn(viewModelScope)
    }

    private fun changeMode(mode: AppConfig) {
        currentMode = mode
        // Update state to reflect mode change
        val currentState = _uiState.value
        if (currentState is DashboardUiState.Success) {
            _uiState.value = currentState.copy(currentMode = mode)
        }
        // Update the service with new mode (convert AppConfig to AppMode)
        LiveUpdateService.start(getApplication(), mode.toAppMode())
    }

    private fun toggleSampling() {
        tickerRepository.toggleSampling()
        val isSampling = tickerRepository.isSamplingEnabled()
        _uiEffect.value = DashboardUiEffect.ShowMessage(
            if (isSampling) "Sampling enabled (500ms)" else "Raw mode (300ms)"
        )
    }

    /**
     * Clears the current UI effect after it has been consumed.
     */
    fun clearEffect() {
        _uiEffect.value = null
    }
}
