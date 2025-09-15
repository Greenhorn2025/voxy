package voxy.friend.chat.common.typegraphy

import androidx.compose.runtime.Composable
import voxy.friend.chat.common.fonts.voxySspTypegraphy
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color

object VoxyTypography {

    // Material 3 Typography using our voxySspTypegraphy()
    @Composable
    fun default() = Typography(
        displayLarge = voxySspTypegraphy().displayLarge,
        displayMedium = voxySspTypegraphy().displayMedium,
        displaySmall = voxySspTypegraphy().displaySmall,
        headlineLarge = voxySspTypegraphy().headlineLarge,
        headlineMedium = voxySspTypegraphy().headlineMedium,
        headlineSmall = voxySspTypegraphy().headlineSmall,
        titleLarge = voxySspTypegraphy().titleLarge,
        titleMedium = voxySspTypegraphy().titleMedium,
        titleSmall = voxySspTypegraphy().titleSmall,
        bodyLarge = voxySspTypegraphy().bodyLarge,
        bodyMedium = voxySspTypegraphy().bodyMedium,
        bodySmall = voxySspTypegraphy().bodySmall,
        labelLarge = voxySspTypegraphy().labelLarge,
        labelMedium = voxySspTypegraphy().labelMedium,
        labelSmall = voxySspTypegraphy().labelSmall
    )

    // Create typography with color
    @Composable
    fun withColor(color: Color) = Typography(
        displayLarge = voxySspTypegraphy().displayLarge.copy(color = color),
        displayMedium = voxySspTypegraphy().displayMedium.copy(color = color),
        displaySmall = voxySspTypegraphy().displaySmall.copy(color = color),
        headlineLarge = voxySspTypegraphy().headlineLarge.copy(color = color),
        headlineMedium = voxySspTypegraphy().headlineMedium.copy(color = color),
        headlineSmall = voxySspTypegraphy().headlineSmall.copy(color = color),
        titleLarge = voxySspTypegraphy().titleLarge.copy(color = color),
        titleMedium = voxySspTypegraphy().titleMedium.copy(color = color),
        titleSmall = voxySspTypegraphy().titleSmall.copy(color = color),
        bodyLarge = voxySspTypegraphy().bodyLarge.copy(color = color),
        bodyMedium = voxySspTypegraphy().bodyMedium.copy(color = color),
        bodySmall = voxySspTypegraphy().bodySmall.copy(color = color),
        labelLarge = voxySspTypegraphy().labelLarge.copy(color = color),
        labelMedium = voxySspTypegraphy().labelMedium.copy(color = color),
        labelSmall = voxySspTypegraphy().labelSmall.copy(color = color)
    )
}