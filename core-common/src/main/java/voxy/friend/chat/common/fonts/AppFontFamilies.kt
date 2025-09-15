package voxy.friend.chat.common.fonts

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import network.chaintech.sdpcomposemultiplatform.ssp
import voxy.friend.chat.common.R

object AppFontFamilies {

    val regular = FontFamily(
        Font(R.font.figtree_regular, FontWeight.Normal),
    )
    val medium = FontFamily(
        Font(R.font.figtree_medium, FontWeight.Normal),
    )
    val semibold = FontFamily(
        Font(R.font.figtree_semi_bold, FontWeight.Normal),
    )
    val italic = FontFamily(
        Font(R.font.figtree_italic, FontWeight.Normal),
    )

}

@Composable
fun voxySspTypegraphy() = Typography(

    // Display Large
    displayLarge = TextStyle(
        fontFamily = AppFontFamilies.semibold,
        fontWeight = FontWeight.Normal,
        fontSize = 57.ssp,
        lineHeight = 64.ssp,
        letterSpacing = (-0.25).ssp
    ),

    // Display Medium
    displayMedium = TextStyle(
        fontFamily = AppFontFamilies.medium,
        fontWeight = FontWeight.Normal,
        fontSize = 45.ssp,
        lineHeight = 52.ssp,
        letterSpacing = 0.ssp
    ),

    // Display Small
    displaySmall = TextStyle(
        fontFamily = AppFontFamilies.regular,
        fontWeight = FontWeight.Normal,
        fontSize = 36.ssp,
        lineHeight = 44.ssp,
        letterSpacing = 0.ssp
    ),

    // Headline Large
    headlineLarge = TextStyle(
        fontFamily = AppFontFamilies.semibold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.ssp,
        lineHeight = 40.ssp,
        letterSpacing = 0.ssp
    ),

    // Headline Medium
    headlineMedium = TextStyle(
        fontFamily = AppFontFamilies.medium,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.ssp,
        lineHeight = 36.ssp,
        letterSpacing = 0.ssp
    ),

    // Headline Small
    headlineSmall = TextStyle(
        fontFamily = AppFontFamilies.regular,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.ssp,
        lineHeight = 32.ssp,
        letterSpacing = 0.ssp
    ),

    // Title Large
    titleLarge = TextStyle(
        fontFamily = AppFontFamilies.semibold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.ssp,
        lineHeight = 28.ssp,
        letterSpacing = 0.ssp
    ),

    // Title Medium
    titleMedium = TextStyle(
        fontFamily = AppFontFamilies.medium,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.ssp,
        lineHeight = 24.ssp,
        letterSpacing = 0.15.ssp
    ),

    // Title Small
    titleSmall = TextStyle(
        fontFamily = AppFontFamilies.regular,
        fontWeight = FontWeight.Bold,
        fontSize = 14.ssp,
        lineHeight = 20.ssp,
        letterSpacing = 0.1.ssp
    ),

    // Body Large
    bodyLarge = TextStyle(
        fontFamily = AppFontFamilies.semibold,
        fontWeight = FontWeight.Normal,
        fontSize = 16.ssp,
        lineHeight = 24.ssp,
        letterSpacing = 0.15.ssp
    ),

    // Body Medium
    bodyMedium = TextStyle(
        fontFamily = AppFontFamilies.medium,
        fontWeight = FontWeight.Normal,
        fontSize = 14.ssp,
        lineHeight = 20.ssp,
        letterSpacing = 0.25.ssp
    ),

    // Body Small
    bodySmall = TextStyle(
        fontFamily = AppFontFamilies.regular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.ssp,
        lineHeight = 16.ssp,
        letterSpacing = 0.4.ssp
    ),

    // Label Large
    labelLarge = TextStyle(
        fontFamily = AppFontFamilies.semibold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.ssp,
        lineHeight = 20.ssp,
        letterSpacing = 0.1.ssp
    ),

    // Label Medium
    labelMedium = TextStyle(
        fontFamily = AppFontFamilies.medium,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.ssp,
        lineHeight = 16.ssp,
        letterSpacing = 0.5.ssp
    ),

    // Label Small
    labelSmall = TextStyle(
        fontFamily = AppFontFamilies.regular,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.ssp,
        lineHeight = 16.ssp,
        letterSpacing = 0.5.ssp
    )
)