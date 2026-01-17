package com.example.pulsepoint.feature.dashboard.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.core.network.model.TickerItem
import com.example.pulsepoint.core.ui.theme.getNegativeTrendColor
import com.example.pulsepoint.core.ui.theme.getPositiveTrendColor
import kotlinx.coroutines.delay

/**
 * Fintech Card Content - Blue/Dark theme for cryptocurrency trading.
 * Shows: Crypto Pair (BTC/USD), Sparkline chart, Volatility badge
 */
@Composable
fun FintechCardContent(
    ticker: TickerItem,
    primaryColor: Color,
    isIncrease: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Header: Icon + Crypto Pair
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Fintech",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Column {
                    Text(
                        text = "${ticker.symbol}/USD",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface // Maximum prominence for main titles
                    )
                    Text(
                        text = "Crypto Pair",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium, // Improved hierarchy
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f) // Semantic token with subtle alpha
                    )
                }
            }

            // Animated Price
            AnimatedContent(
                targetState = ticker.currentValue,
                transitionSpec = {
                    val slideDirection = if (isIncrease) 1 else -1
                    (
                        slideInVertically(
                            animationSpec = tween(300),
                            initialOffsetY = { fullHeight -> fullHeight * slideDirection }
                        ) + fadeIn(animationSpec = tween(300))
                    ) togetherWith (
                        slideOutVertically(
                            animationSpec = tween(300),
                            targetOffsetY = { fullHeight -> -fullHeight * slideDirection }
                        ) + fadeOut(animationSpec = tween(300))
                    )
                },
                label = "priceAnimation"
            ) { currentValue ->
                Column(horizontalAlignment = Alignment.End) {
                    val trendColor = if (ticker.isPositiveChange) getPositiveTrendColor() else getNegativeTrendColor()
                    Text(
                        text = String.format("$%.2f", currentValue),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = trendColor // Keep trend color for price to show direction
                    )
                    Text(
                        text = "${if (ticker.change >= 0) "+" else ""}${ticker.changePercentFormatted}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = trendColor
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Sparkline Chart
        if (ticker.history.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Sparkline(
                    data = ticker.history,
                    lineColor = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2f
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Volatility Badge - High contrast background with light text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.surface) // High contrast dark surface
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Volatility: ${String.format("%.2f%%", kotlin.math.abs(ticker.changePercent))}",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface // Light text for maximum contrast
                )
            }
        }
    }
}

/**
 * Sports Card Content - Orange theme for live sports scores.
 * Shows: Team Names (Lakers vs. Celtics), Game Clock, Pulsing Live icon
 */
@Composable
fun SportsCardContent(
    ticker: TickerItem,
    primaryColor: Color,
    isIncrease: Boolean,
    modifier: Modifier = Modifier
) {
    // Map ticker symbol to team names
    val teamName = mapTickerToTeamName(ticker.symbol)
    val opponentName = mapTickerToOpponentName(ticker.symbol)

    Column(modifier = modifier) {
        // Header: Live Badge + Team Matchup
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                // Pulsing Live Icon
                PulsingLiveIcon(
                    color = getNegativeTrendColor(), // Uses theme-appropriate Live badge color
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "$teamName vs $opponentName",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface // Maximum prominence for main titles
                )
                Text(
                    text = "Live Match",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium, // Improved hierarchy
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f) // Semantic token with subtle alpha
                )
            }

            // Game Clock
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = formatGameClock(ticker.currentValue),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface // Maximum prominence
                )
                Text(
                    text = "Q${((ticker.currentValue.toInt() / 300) % 4) + 1}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f) // Semantic token
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Score Display
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = teamName.take(3).uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                AnimatedContent(
                    targetState = ticker.currentValue.toInt(),
                    transitionSpec = {
                        val slideDirection = if (isIncrease) 1 else -1
                        (
                            slideInVertically(
                                animationSpec = tween(300),
                                initialOffsetY = { fullHeight -> fullHeight * slideDirection }
                            ) + fadeIn(animationSpec = tween(300))
                        ) togetherWith (
                            slideOutVertically(
                                animationSpec = tween(300),
                                targetOffsetY = { fullHeight -> -fullHeight * slideDirection }
                            ) + fadeOut(animationSpec = tween(300))
                        )
                    },
                    label = "scoreAnimation"
                ) { score ->
                    Text(
                        text = score.toString(),
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface // Maximum prominence
                    )
                }
            }

            Text(
                text = "—",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = opponentName.take(3).uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                AnimatedContent(
                    targetState = (ticker.currentValue / 10).toInt(),
                    transitionSpec = {
                        val slideDirection = if (isIncrease) 1 else -1
                        (
                            slideInVertically(
                                animationSpec = tween(300),
                                initialOffsetY = { fullHeight -> fullHeight * slideDirection }
                            ) + fadeIn(animationSpec = tween(300))
                        ) togetherWith (
                            slideOutVertically(
                                animationSpec = tween(300),
                                targetOffsetY = { fullHeight -> -fullHeight * slideDirection }
                            ) + fadeOut(animationSpec = tween(300))
                        )
                    },
                    label = "opponentScoreAnimation"
                ) { score ->
                    Text(
                        text = score.toString(),
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface // Maximum prominence
                    )
                }
            }
        }
    }
}

/**
 * Rewards Card Content - Green theme for points and rewards.
 * Shows: Points Earned, LinearProgressIndicator, Star icon
 */
@Composable
fun RewardsCardContent(
    ticker: TickerItem,
    primaryColor: Color,
    isIncrease: Boolean,
    modifier: Modifier = Modifier
) {
    // Map ticker to reward name
    val rewardName = mapTickerToRewardName(ticker.symbol)
    
    Column(modifier = modifier) {
        // Header: Icon + Reward Name
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rewards",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Column {
                    Text(
                        text = rewardName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface // Maximum prominence for main titles
                    )
                    Text(
                        text = "Points Earned",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium, // Improved hierarchy
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f) // Semantic token with subtle alpha
                    )
                }
            }

            // Animated Points
            AnimatedContent(
                targetState = ticker.currentValue,
                transitionSpec = {
                    val slideDirection = if (isIncrease) 1 else -1
                    (
                        slideInVertically(
                            animationSpec = tween(300),
                            initialOffsetY = { fullHeight -> fullHeight * slideDirection }
                        ) + fadeIn(animationSpec = tween(300))
                    ) togetherWith (
                        slideOutVertically(
                            animationSpec = tween(300),
                            targetOffsetY = { fullHeight -> -fullHeight * slideDirection }
                        ) + fadeOut(animationSpec = tween(300))
                    )
                },
                label = "pointsAnimation"
            ) { currentValue ->
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "${currentValue.toInt()} pts",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface // Maximum prominence
                    )
                    // Animated points change with scale-in for gamified feel
                    AnimatedContent(
                        targetState = ticker.change.toInt(),
                        transitionSpec = {
                            // Use fade + slide combination to create a "pop" effect similar to scale
                            (
                                fadeIn(animationSpec = tween(200)) +
                                slideInVertically(
                                    animationSpec = tween(200),
                                    initialOffsetY = { height -> (height * 0.3).toInt() }
                                )
                            ) togetherWith (
                                fadeOut(animationSpec = tween(150)) +
                                slideOutVertically(
                                    animationSpec = tween(150),
                                    targetOffsetY = { height -> (-height * 0.1).toInt() }
                                )
                            )
                        },
                        label = "pointsChangeAnimation"
                    ) { change ->
                        Text(
                            text = "+$change today",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold, // Bold for emphasis
                            color = MaterialTheme.colorScheme.onTertiaryContainer, // High-contrast color for accent text
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Progress Indicator (ONLY in Rewards Mode)
        val progress = ((ticker.currentValue % 1000.0) / 1000.0).toFloat().coerceIn(0f, 1f)
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Points to next tier: ${(1000 - (ticker.currentValue % 1000)).toInt()}",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f) // Semantic token
        )
    }
}

// Helper functions for data sanitization
private fun mapTickerToTeamName(symbol: String): String {
    return when (symbol) {
        "BTC" -> "Lakers"
        "ETH" -> "Warriors"
        "SOL" -> "Celtics"
        "BNB" -> "Heat"
        "ADA" -> "Nuggets"
        else -> "Team ${symbol.take(3)}"
    }
}

private fun mapTickerToOpponentName(symbol: String): String {
    return when (symbol) {
        "BTC" -> "Celtics"
        "ETH" -> "Heat"
        "SOL" -> "Lakers"
        "BNB" -> "Warriors"
        "ADA" -> "Suns"
        else -> "Opponent"
    }
}

private fun mapTickerToRewardName(symbol: String): String {
    return when (symbol) {
        "BTC" -> "Bonus Points"
        "ETH" -> "Referral Reward"
        "SOL" -> "Daily Challenge"
        "BNB" -> "Achievement Unlock"
        "ADA" -> "Loyalty Bonus"
        else -> "Reward Points"
    }
}

private fun formatGameClock(value: Double): String {
    val totalSeconds = (value.toInt() % 720) // 12-minute quarters
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%d:%02d", minutes, seconds)
}

/**
 * Pulsing Live Icon for Sports Mode
 */
@Composable
private fun PulsingLiveIcon(
    color: Color,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    // Use high-contrast surface background with pulsing alpha, but ensure text is always high contrast
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(
                // Use surface color with pulsing alpha for animation, but ensure it's dark enough for contrast
                MaterialTheme.colorScheme.surface.copy(alpha = alpha.coerceAtLeast(0.9f))
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "● LIVE",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface // High contrast light text on dark surface
        )
    }
}
