package com.example.pulsepoint.core.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Centralized color system for PulsePoint with WCAG accessibility-compliant palettes.
 * Each mode (Fintech, Sports, Rewards) has distinct, professional color schemes.
 */

// ============ FINTECH MODE COLORS ============
// Trustworthy blues, slate grays, with accessible trend indicators

object FintechColors {
    // Primary palette - Trustworthy blues
    val PrimaryLight = Color(0xFF1976D2) // Material Blue 700
    val PrimaryDark = Color(0xFF90CAF9)  // Material Blue 200
    
    // Surface colors - Clean, professional
    val SurfaceLight = Color(0xFFFFFFFF)
    val SurfaceDark = Color(0xFF121212)
    val SurfaceVariantLight = Color(0xFFF5F5F5)
    val SurfaceVariantDark = Color(0xFF1E1E1E)
    
    // Text colors - High contrast for readability
    val OnSurfaceLight = Color(0xFF1C1C1C)
    val OnSurfaceDark = Color(0xFFE0E0E0)
    val OnSurfaceVariantLight = Color(0xFF616161) // Gray 700
    val OnSurfaceVariantDark = Color(0xFFB0B0B0)
    
    // Trend indicators - Accessible green/red (not pure RGB)
    val PositiveTrendLight = Color(0xFF2E7D32) // Green 800 - darker, more readable
    val PositiveTrendDark = Color(0xFF81C784)  // Green 300
    val NegativeTrendLight = Color(0xFFC62828) // Red 800 - darker, more readable
    val NegativeTrendDark = Color(0xFFE57373)  // Red 300
    
    // Accent colors
    val AccentLight = Color(0xFF546E7A) // Blue Grey 600
    val AccentDark = Color(0xFF78909C)  // Blue Grey 400
}

// ============ SPORTS MODE COLORS ============
// Energetic but readable team colors on neutral backgrounds

object SportsColors {
    // Primary palette - Energetic orange/deep blue
    val PrimaryLight = Color(0xFFFF6F00) // Orange 800 - deep, readable
    val PrimaryDark = Color(0xFFFFB74D)  // Orange 300
    
    // Surface colors - Neutral card backgrounds
    val SurfaceLight = Color(0xFFFFFBFE)
    val SurfaceDark = Color(0xFF1C1B1F)
    val SurfaceVariantLight = Color(0xFFF5F5F5)
    val SurfaceVariantDark = Color(0xFF2A2A2A)
    
    // Text colors - High contrast
    val OnSurfaceLight = Color(0xFF1C1C1C)
    val OnSurfaceDark = Color(0xFFE0E0E0)
    val OnSurfaceVariantLight = Color(0xFF757575) // Gray 600
    val OnSurfaceVariantDark = Color(0xFFB0B0B0)
    
    // Team score colors - Deep, readable
    val TeamScoreLight = Color(0xFF1565C0) // Blue 800
    val TeamScoreDark = Color(0xFF64B5F6)  // Blue 300
    val LiveBadgeLight = Color(0xFFD32F2F) // Red 700
    val LiveBadgeDark = Color(0xFFEF5350)  // Red 400
    
    // Accent colors
    val AccentLight = Color(0xFF424242) // Gray 800
    val AccentDark = Color(0xFF757575)  // Gray 600
}

// ============ REWARDS MODE COLORS ============
// Inviting, cheerful colors (purples, golds, teals)

object RewardsColors {
    // Primary palette - Inviting purple
    val PrimaryLight = Color(0xFF7B1FA2) // Purple 700 - inviting but readable
    val PrimaryDark = Color(0xFFBA68C8)  // Purple 300
    
    // Surface colors - Warm, inviting
    val SurfaceLight = Color(0xFFFFFBFE)
    val SurfaceDark = Color(0xFF1C1B1F)
    val SurfaceVariantLight = Color(0xFFF3E5F5) // Light purple tint
    val SurfaceVariantDark = Color(0xFF2D1B2E)
    
    // Text colors - High contrast
    val OnSurfaceLight = Color(0xFF1C1C1C)
    val OnSurfaceDark = Color(0xFFE0E0E0)
    val OnSurfaceVariantLight = Color(0xFF6A6A6A)
    val OnSurfaceVariantDark = Color(0xFFB0B0B0)
    
    // Reward indicators - Cheerful gold/teal
    val RewardAccentLight = Color(0xFFF57C00) // Orange 700 - gold-like
    val RewardAccentDark = Color(0xFFFFB74D)  // Orange 300
    val ProgressTrackLight = Color(0xFFE0E0E0) // Gray 300
    val ProgressTrackDark = Color(0xFF424242)  // Gray 800
    
    // Accent colors
    val AccentLight = Color(0xFF00897B) // Teal 600
    val AccentDark = Color(0xFF4DB6AC)  // Teal 300
}

// ============ COMMON COLORS ============
object CommonColors {
    val Error = Color(0xFFB00020)
    val ErrorDark = Color(0xFFCF6679)
    
    val BackgroundLight = Color(0xFFFFFBFE)
    val BackgroundDark = Color(0xFF121212)
}
