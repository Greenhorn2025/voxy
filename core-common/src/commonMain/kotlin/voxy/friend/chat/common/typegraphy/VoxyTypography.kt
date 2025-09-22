package voxy.friend.chat.common.typegraphy

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import voxy.friend.chat.common.fonts.voxySspTypography

object VoxyTypography {

    // Material 3 Typography using our voxySspTypegraphy()
    @Composable
    fun default() = Typography(
        displayLarge = voxySspTypography().displayLarge,
        displayMedium = voxySspTypography().displayMedium,
        displaySmall = voxySspTypography().displaySmall,
        headlineLarge = voxySspTypography().headlineLarge,
        headlineMedium = voxySspTypography().headlineMedium,
        headlineSmall = voxySspTypography().headlineSmall,
        titleLarge = voxySspTypography().titleLarge,
        titleMedium = voxySspTypography().titleMedium,
        titleSmall = voxySspTypography().titleSmall,
        bodyLarge = voxySspTypography().bodyLarge,
        bodyMedium = voxySspTypography().bodyMedium,
        bodySmall = voxySspTypography().bodySmall,
        labelLarge = voxySspTypography().labelLarge,
        labelMedium = voxySspTypography().labelMedium,
        labelSmall = voxySspTypography().labelSmall
    )

    // Create typography with color
    @Composable
    fun withColor(color: Color) = Typography(
        displayLarge = voxySspTypography().displayLarge.copy(color = color),
        displayMedium = voxySspTypography().displayMedium.copy(color = color),
        displaySmall = voxySspTypography().displaySmall.copy(color = color),
        headlineLarge = voxySspTypography().headlineLarge.copy(color = color),
        headlineMedium = voxySspTypography().headlineMedium.copy(color = color),
        headlineSmall = voxySspTypography().headlineSmall.copy(color = color),
        titleLarge = voxySspTypography().titleLarge.copy(color = color),
        titleMedium = voxySspTypography().titleMedium.copy(color = color),
        titleSmall = voxySspTypography().titleSmall.copy(color = color),
        bodyLarge = voxySspTypography().bodyLarge.copy(color = color),
        bodyMedium = voxySspTypography().bodyMedium.copy(color = color),
        bodySmall = voxySspTypography().bodySmall.copy(color = color),
        labelLarge = voxySspTypography().labelLarge.copy(color = color),
        labelMedium = voxySspTypography().labelMedium.copy(color = color),
        labelSmall = voxySspTypography().labelSmall.copy(color = color)
    )
}