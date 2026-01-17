package com.example.pulsepoint.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * App mode enum for theming.
 * Maps to feature-specific AppConfig in feature modules.
 */
enum class AppMode {
    FINTECH_MODE,
    SPORTS_MODE,
    REWARDS_MODE
}

/**
 * Dynamic theme engine that applies industry-specific color schemes based on AppMode.
 * Uses WCAG-compliant color palettes for accessibility.
 */

// Local composition key for current app mode
val LocalAppMode = compositionLocalOf<AppMode> {
    error("No AppMode provided")
}

/**
 * Gets the appropriate color scheme based on the current app mode and theme.
 */
private fun getColorScheme(
    mode: AppMode,
    darkTheme: Boolean
): androidx.compose.material3.ColorScheme {
    return when (mode) {
        AppMode.FINTECH_MODE -> {
            if (darkTheme) {
                darkColorScheme(
                    primary = FintechColors.PrimaryDark,
                    secondary = FintechColors.AccentDark,
                    tertiary = FintechColors.AccentDark,
                    surface = FintechColors.SurfaceDark,
                    surfaceVariant = FintechColors.SurfaceVariantDark,
                    secondaryContainer = FintechColors.SurfaceVariantDark.copy(alpha = 0.6f), // Softer container
                    tertiaryContainer = FintechColors.SurfaceVariantDark.copy(alpha = 0.4f),
                    onPrimary = Color.White,
                    onSecondary = Color.White,
                    onTertiary = Color.White,
                    onSurface = FintechColors.OnSurfaceDark,
                    onSurfaceVariant = FintechColors.OnSurfaceVariantDark,
                    onSecondaryContainer = FintechColors.OnSurfaceDark.copy(alpha = 0.9f), // High contrast text
                    onTertiaryContainer = FintechColors.OnSurfaceDark.copy(alpha = 0.8f),
                    background = FintechColors.SurfaceDark,
                    error = CommonColors.ErrorDark
                )
            } else {
                lightColorScheme(
                    primary = FintechColors.PrimaryLight,
                    secondary = FintechColors.AccentLight,
                    tertiary = FintechColors.AccentLight,
                    surface = FintechColors.SurfaceLight,
                    surfaceVariant = FintechColors.SurfaceVariantLight,
                    secondaryContainer = FintechColors.SurfaceVariantLight.copy(alpha = 0.5f), // Softer container
                    tertiaryContainer = FintechColors.SurfaceVariantLight.copy(alpha = 0.3f),
                    onPrimary = Color.White,
                    onSecondary = Color.White,
                    onTertiary = Color.White,
                    onSurface = FintechColors.OnSurfaceLight,
                    onSurfaceVariant = FintechColors.OnSurfaceVariantLight,
                    onSecondaryContainer = FintechColors.OnSurfaceLight.copy(alpha = 0.9f), // High contrast text
                    onTertiaryContainer = FintechColors.OnSurfaceLight.copy(alpha = 0.8f),
                    background = FintechColors.SurfaceLight,
                    error = CommonColors.Error
                )
            }
        }

        AppMode.SPORTS_MODE -> {
            if (darkTheme) {
                darkColorScheme(
                    primary = SportsColors.PrimaryDark,
                    secondary = SportsColors.AccentDark,
                    tertiary = SportsColors.AccentDark,
                    surface = SportsColors.SurfaceDark,
                    surfaceVariant = SportsColors.SurfaceVariantDark,
                    secondaryContainer = SportsColors.SurfaceVariantDark.copy(alpha = 0.6f), // Softer container
                    tertiaryContainer = SportsColors.SurfaceVariantDark.copy(alpha = 0.4f),
                    onPrimary = Color.Black,
                    onSecondary = Color.Black,
                    onTertiary = Color.Black,
                    onSurface = SportsColors.OnSurfaceDark,
                    onSurfaceVariant = SportsColors.OnSurfaceVariantDark,
                    onSecondaryContainer = SportsColors.OnSurfaceDark.copy(alpha = 0.9f), // High contrast text
                    onTertiaryContainer = SportsColors.OnSurfaceDark.copy(alpha = 0.8f),
                    background = SportsColors.SurfaceDark,
                    error = CommonColors.ErrorDark
                )
            } else {
                lightColorScheme(
                    primary = SportsColors.PrimaryLight,
                    secondary = SportsColors.AccentLight,
                    tertiary = SportsColors.AccentLight,
                    surface = SportsColors.SurfaceLight,
                    surfaceVariant = SportsColors.SurfaceVariantLight,
                    secondaryContainer = SportsColors.SurfaceVariantLight.copy(alpha = 0.5f), // Softer container
                    tertiaryContainer = SportsColors.SurfaceVariantLight.copy(alpha = 0.3f),
                    onPrimary = Color.White,
                    onSecondary = Color.White,
                    onTertiary = Color.White,
                    onSurface = SportsColors.OnSurfaceLight,
                    onSurfaceVariant = SportsColors.OnSurfaceVariantLight,
                    onSecondaryContainer = SportsColors.OnSurfaceLight.copy(alpha = 0.9f), // High contrast text
                    onTertiaryContainer = SportsColors.OnSurfaceLight.copy(alpha = 0.8f),
                    background = SportsColors.SurfaceLight,
                    error = CommonColors.Error
                )
            }
        }

        AppMode.REWARDS_MODE -> {
            if (darkTheme) {
                darkColorScheme(
                    primary = RewardsColors.PrimaryDark,
                    secondary = RewardsColors.AccentDark,
                    tertiary = RewardsColors.RewardAccentDark,
                    surface = RewardsColors.SurfaceDark,
                    surfaceVariant = RewardsColors.SurfaceVariantDark,
                    secondaryContainer = RewardsColors.SurfaceVariantDark.copy(alpha = 0.6f), // Softer container
                    tertiaryContainer = RewardsColors.SurfaceVariantDark.copy(alpha = 0.4f),
                    onPrimary = Color.Black,
                    onSecondary = Color.White,
                    onTertiary = Color.Black,
                    onSurface = RewardsColors.OnSurfaceDark,
                    onSurfaceVariant = RewardsColors.OnSurfaceVariantDark,
                    onSecondaryContainer = RewardsColors.OnSurfaceDark.copy(alpha = 0.9f), // High contrast text
                    onTertiaryContainer = RewardsColors.OnSurfaceDark.copy(alpha = 0.8f),
                    background = RewardsColors.SurfaceDark,
                    error = CommonColors.ErrorDark
                )
            } else {
                lightColorScheme(
                    primary = RewardsColors.PrimaryLight,
                    secondary = RewardsColors.AccentLight,
                    tertiary = RewardsColors.RewardAccentLight,
                    surface = RewardsColors.SurfaceLight,
                    surfaceVariant = RewardsColors.SurfaceVariantLight,
                    secondaryContainer = RewardsColors.SurfaceVariantLight.copy(alpha = 0.5f), // Softer container
                    tertiaryContainer = RewardsColors.SurfaceVariantLight.copy(alpha = 0.3f),
                    onPrimary = Color.White,
                    onSecondary = Color.White,
                    onTertiary = Color.White,
                    onSurface = RewardsColors.OnSurfaceLight,
                    onSurfaceVariant = RewardsColors.OnSurfaceVariantLight,
                    onSecondaryContainer = RewardsColors.OnSurfaceLight.copy(alpha = 0.9f), // High contrast text
                    onTertiaryContainer = RewardsColors.OnSurfaceLight.copy(alpha = 0.8f),
                    background = RewardsColors.SurfaceLight,
                    error = CommonColors.Error
                )
            }
        }
    }
}

/**
 * PulsePoint dynamic theme that adapts based on the current AppMode.
 * 
 * @param currentMode The current app mode (Fintech, Sports, or Rewards)
 * @param darkTheme Whether to use dark theme
 * @param content The composable content to wrap with the theme
 */
@Composable
fun PulsePointTheme(
    currentMode: AppMode,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = getColorScheme(currentMode, darkTheme)

    CompositionLocalProvider(LocalAppMode provides currentMode) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

/**
 * Extension function to get mode-specific trend colors.
 */
@Composable
fun getPositiveTrendColor(): androidx.compose.ui.graphics.Color {
    val mode = LocalAppMode.current
    val isDark = isSystemInDarkTheme()
    
    return when (mode) {
        AppMode.FINTECH_MODE -> if (isDark) FintechColors.PositiveTrendDark else FintechColors.PositiveTrendLight
        AppMode.SPORTS_MODE -> if (isDark) SportsColors.TeamScoreDark else SportsColors.TeamScoreLight
        AppMode.REWARDS_MODE -> if (isDark) RewardsColors.RewardAccentDark else RewardsColors.RewardAccentLight
    }
}

/**
 * Extension function to get mode-specific negative trend colors.
 */
@Composable
fun getNegativeTrendColor(): androidx.compose.ui.graphics.Color {
    val mode = LocalAppMode.current
    val isDark = isSystemInDarkTheme()
    
    return when (mode) {
        AppMode.FINTECH_MODE -> if (isDark) FintechColors.NegativeTrendDark else FintechColors.NegativeTrendLight
        AppMode.SPORTS_MODE -> if (isDark) SportsColors.LiveBadgeDark else SportsColors.LiveBadgeLight
        AppMode.REWARDS_MODE -> if (isDark) RewardsColors.RewardAccentDark else RewardsColors.RewardAccentLight
    }
}
