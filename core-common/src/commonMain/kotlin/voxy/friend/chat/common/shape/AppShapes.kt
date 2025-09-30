package voxy.friend.chat.common.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

object AppShapes {

    // ============== Uniform Rounded Corners ==============

    /** No rounded corners - perfect rectangle */
    val None = RoundedCornerShape(0.dp)

    /** Extra small rounded corners - 2dp */
    val ExtraSmall = RoundedCornerShape(2.dp)

    /** Small rounded corners - 4dp */
    val Small = RoundedCornerShape(4.dp)

    /** Small-Medium rounded corners - 6dp */
    val SmallMedium = RoundedCornerShape(6.dp)

    /** Medium rounded corners - 8dp */
    val Medium = RoundedCornerShape(8.dp)

    /** Medium-Large rounded corners - 10dp */
    val MediumLarge = RoundedCornerShape(10.dp)

    /** Standard rounded corners - 12dp */
    val Standard = RoundedCornerShape(12.dp)

    /** Large rounded corners - 16dp */
    val Large = RoundedCornerShape(16.dp)

    /** Extra large rounded corners - 20dp */
    val ExtraLarge = RoundedCornerShape(20.dp)

    /** XXL rounded corners - 24dp */
    val XXLarge = RoundedCornerShape(24.dp)

    /** XXXL rounded corners - 28dp */
    val XXXLarge = RoundedCornerShape(28.dp)

    /** Huge rounded corners - 32dp */
    val Huge = RoundedCornerShape(32.dp)

    /** Massive rounded corners - 40dp */
    val Massive = RoundedCornerShape(40.dp)

    /** Full/Circular rounded corners - 50% */
    val Full = RoundedCornerShape(50)


    // ============== Top Corners Only ==============

    /** Top corners only - Small (4dp) */
    val TopSmall = RoundedCornerShape(
        topStart = 4.dp,
        topEnd = 4.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    /** Top corners only - Medium (8dp) */
    val TopMedium = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 8.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    /** Top corners only - Large (16dp) */
    val TopLarge = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    /** Top corners only - Extra Large (24dp) */
    val TopExtraLarge = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 24.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )


    // ============== Bottom Corners Only ==============

    /** Bottom corners only - Small (4dp) */
    val BottomSmall = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 4.dp,
        bottomEnd = 4.dp
    )

    /** Bottom corners only - Medium (8dp) */
    val BottomMedium = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 8.dp,
        bottomEnd = 8.dp
    )

    /** Bottom corners only - Large (16dp) */
    val BottomLarge = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 16.dp,
        bottomEnd = 16.dp
    )

    /** Bottom corners only - Extra Large (24dp) */
    val BottomExtraLarge = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 24.dp,
        bottomEnd = 24.dp
    )


    // ============== Start (Left) Corners Only ==============

    /** Start corners only - Small (4dp) */
    val StartSmall = RoundedCornerShape(
        topStart = 4.dp,
        topEnd = 0.dp,
        bottomStart = 4.dp,
        bottomEnd = 0.dp
    )

    /** Start corners only - Medium (8dp) */
    val StartMedium = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 0.dp,
        bottomStart = 8.dp,
        bottomEnd = 0.dp
    )

    /** Start corners only - Large (16dp) */
    val StartLarge = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 0.dp,
        bottomStart = 16.dp,
        bottomEnd = 0.dp
    )


    // ============== End (Right) Corners Only ==============

    /** End corners only - Small (4dp) */
    val EndSmall = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 4.dp,
        bottomStart = 0.dp,
        bottomEnd = 4.dp
    )

    /** End corners only - Medium (8dp) */
    val EndMedium = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 8.dp,
        bottomStart = 0.dp,
        bottomEnd = 8.dp
    )

    /** End corners only - Large (16dp) */
    val EndLarge = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 16.dp,
        bottomStart = 0.dp,
        bottomEnd = 16.dp
    )


    // ============== Diagonal Corners ==============

    /** Top-Start and Bottom-End corners - Medium (8dp) */
    val DiagonalMedium = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 0.dp,
        bottomStart = 0.dp,
        bottomEnd = 8.dp
    )

    /** Top-End and Bottom-Start corners - Medium (8dp) */
    val DiagonalReverseMedium = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 8.dp,
        bottomStart = 8.dp,
        bottomEnd = 0.dp
    )

    /** Top-Start and Bottom-End corners - Large (16dp) */
    val DiagonalLarge = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 0.dp,
        bottomStart = 0.dp,
        bottomEnd = 16.dp
    )


    // ============== Single Corner Only ==============

    /** Only Top-Start corner - Medium (8dp) */
    val TopStartMedium = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 0.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    /** Only Top-End corner - Medium (8dp) */
    val TopEndMedium = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 8.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    /** Only Bottom-Start corner - Medium (8dp) */
    val BottomStartMedium = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 8.dp,
        bottomEnd = 0.dp
    )

    /** Only Bottom-End corner - Medium (8dp) */
    val BottomEndMedium = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 0.dp,
        bottomEnd = 8.dp
    )


    // ============== Custom/Special Shapes ==============

    /** Pill/Stadium shape - typically for buttons */
    val Pill = RoundedCornerShape(50)

    /** Card shape - standard for cards */
    val Card = RoundedCornerShape(12.dp)

    /** Dialog shape - for dialog windows */
    val Dialog = RoundedCornerShape(28.dp)

    /** Bottom sheet shape - for bottom sheets */
    val BottomSheet = RoundedCornerShape(
        topStart = 28.dp,
        topEnd = 28.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    /** FAB shape - for Floating Action Buttons */
    val Fab = RoundedCornerShape(16.dp)

    /** Chip shape - for chip components */
    val Chip = RoundedCornerShape(8.dp)

    /** TextField shape - for input fields */
    val TextField = RoundedCornerShape(4.dp)

    /** Asymmetric shape - different radius for each corner */
    val Asymmetric = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 8.dp,
        bottomStart = 16.dp,
        bottomEnd = 4.dp
    )


    // ============== Helper Functions ==============

    /**
     * Create a custom rounded corner shape with uniform radius
     * @param radius The radius in dp for all corners
     */
    fun custom(radius: Int) = RoundedCornerShape(radius.dp)

    /**
     * Create a custom rounded corner shape with individual corner radius
     * @param topStart Radius for top-start corner in dp
     * @param topEnd Radius for top-end corner in dp
     * @param bottomStart Radius for bottom-start corner in dp
     * @param bottomEnd Radius for bottom-end corner in dp
     */
    fun custom(
        topStart: Int = 0,
        topEnd: Int = 0,
        bottomStart: Int = 0,
        bottomEnd: Int = 0
    ) = RoundedCornerShape(
        topStart = topStart.dp,
        topEnd = topEnd.dp,
        bottomStart = bottomStart.dp,
        bottomEnd = bottomEnd.dp
    )

    /**
     * Create a rounded corner shape with only top corners
     * @param radius The radius in dp for top corners
     */
    fun topOnly(radius: Int) = RoundedCornerShape(
        topStart = radius.dp,
        topEnd = radius.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    /**
     * Create a rounded corner shape with only bottom corners
     * @param radius The radius in dp for bottom corners
     */
    fun bottomOnly(radius: Int) = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = radius.dp,
        bottomEnd = radius.dp
    )

    /**
     * Create a rounded corner shape with only start (left) corners
     * @param radius The radius in dp for start corners
     */
    fun startOnly(radius: Int) = RoundedCornerShape(
        topStart = radius.dp,
        topEnd = 0.dp,
        bottomStart = radius.dp,
        bottomEnd = 0.dp
    )

    /**
     * Create a rounded corner shape with only end (right) corners
     * @param radius The radius in dp for end corners
     */
    fun endOnly(radius: Int) = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = radius.dp,
        bottomStart = 0.dp,
        bottomEnd = radius.dp
    )
}