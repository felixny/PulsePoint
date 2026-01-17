package com.example.pulsepoint.feature.dashboard.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.core.network.model.TickerItem
import com.example.pulsepoint.core.ui.theme.getNegativeTrendColor
import com.example.pulsepoint.core.ui.theme.getPositiveTrendColor
import com.example.pulsepoint.feature.dashboard.model.AppConfig
import kotlinx.coroutines.delay

/**
 * Factory Pattern Implementation: Animated Ticker Card with strict industry-specific skins.
 * Uses AnimatedContent for smooth transitions when switching between modes.
 * Each mode renders completely unique content with no overlapping text or styles.
 */
@Composable
fun AnimatedTickerCard(
    ticker: TickerItem,
    currentMode: AppConfig,
    modifier: Modifier = Modifier
) {
    var previousValue by remember(ticker.id) { mutableStateOf(ticker.currentValue) }
    var flashColor by remember { mutableStateOf<Color?>(null) }

    // Determine if value increased or decreased
    val isIncrease = ticker.currentValue > previousValue

    // Get theme-appropriate trend colors for flash effect
    val positiveTrendColor = getPositiveTrendColor()
    val negativeTrendColor = getNegativeTrendColor()

    // Trigger flash color animation when value changes
    LaunchedEffect(ticker.currentValue) {
        if (ticker.currentValue != previousValue) {
            flashColor = if (isIncrease) positiveTrendColor else negativeTrendColor
            delay(300) // Flash duration
            flashColor = null
            previousValue = ticker.currentValue
        }
    }

    // Animate background color for flash effect (overrides theme during flash)
    // Use secondaryContainer with 80% alpha or tonal elevation for softer, more readable backgrounds
    val backgroundColor by animateColorAsState(
        targetValue = flashColor ?: MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
        animationSpec = tween(durationMillis = 150),
        label = "flashColor"
    )

    // Use Card with elevation for Material 3 design system compliance
    // The 80% alpha on backgroundColor already softens the background while maintaining color identity
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Softens background while maintaining color identity
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            // Factory Pattern: Use AnimatedContent for smooth mode transitions
            AnimatedContent(
                targetState = currentMode,
                transitionSpec = {
                    // Fade and slide transition when mode changes
                    (
                        fadeIn(animationSpec = tween(400)) +
                        slideInVertically(
                            animationSpec = tween(400),
                            initialOffsetY = { height -> height / 3 }
                        )
                    ) togetherWith (
                        fadeOut(animationSpec = tween(300)) +
                        slideOutVertically(
                            animationSpec = tween(300),
                            targetOffsetY = { height -> -height / 3 }
                        )
                    )
                },
                label = "modeTransition"
            ) { mode ->
                // Factory: Determine which industry-specific content to render
                // Components now use MaterialTheme.colorScheme directly, no need to pass primaryColor
                when (mode) {
                    AppConfig.FINTECH_MODE -> {
                        FintechCardContent(
                            ticker = ticker,
                            primaryColor = MaterialTheme.colorScheme.primary, // For compatibility, but components use theme directly
                            isIncrease = isIncrease,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    AppConfig.SPORTS_MODE -> {
                        SportsCardContent(
                            ticker = ticker,
                            primaryColor = MaterialTheme.colorScheme.primary,
                            isIncrease = isIncrease,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    AppConfig.REWARDS_MODE -> {
                        RewardsCardContent(
                            ticker = ticker,
                            primaryColor = MaterialTheme.colorScheme.primary,
                            isIncrease = isIncrease,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
