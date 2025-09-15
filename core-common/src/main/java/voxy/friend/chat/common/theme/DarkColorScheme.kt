package voxy.friend.chat.common.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import voxy.friend.chat.common.color.AppColors

private val DarkColorScheme = darkColorScheme(
    primary = AppColors.Primary,
    onPrimary = AppColors.OnPrimary,
    primaryContainer = AppColors.PrimaryMuted,
    onPrimaryContainer = AppColors.Inverse,

    secondary = AppColors.Secondary,
    onSecondary = AppColors.OnSecondary,
    secondaryContainer = AppColors.Tertiary,
    onSecondaryContainer = AppColors.Inverse,

    tertiary = AppColors.Tertiary,
    onTertiary = AppColors.OnTertiary,
    tertiaryContainer = AppColors.PrimaryMuted,
    onTertiaryContainer = AppColors.Inverse,

    error = AppColors.Error,
    onError = AppColors.OnError,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    background = AppColors.Bg,
    onBackground = AppColors.OnBackground,

    surface = AppColors.Surface,
    onSurface = AppColors.OnSurface,
    surfaceVariant = AppColors.SurfaceVariant,
    onSurfaceVariant = Color(0xFFCAC4D0),

    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),

    scrim = Color(0xFF000000),
    inverseSurface = AppColors.Inverse,
    inverseOnSurface = AppColors.Primary,
    inversePrimary = Color(0xFF6750A4),

    surfaceDim = Color(0xFF0F0F0F),
    surfaceBright = Color(0xFF2B2B2B),
    surfaceContainerLowest = Color(0xFF0A0A0A),
    surfaceContainerLow = Color(0xFF1A1A1A),
    surfaceContainer = AppColors.Secondary,
    surfaceContainerHigh = Color(0xFF262626),
    surfaceContainerHighest = Color(0xFF313131)
)