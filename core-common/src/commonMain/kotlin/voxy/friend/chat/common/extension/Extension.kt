package voxy.friend.chat.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

@Composable
fun DisplayIf(visible: Boolean, content: @Composable () -> Unit) {
    if (visible) {
        content()
    }
}

@Composable
fun DisplayIf(
    visible: Boolean,
    content: @Composable () -> Unit,
    elseContent: @Composable () -> Unit
) {
    if (visible) {
        content()
    } else
        elseContent()
}

fun DrawScope.drawChatBubble(isSender: Boolean) {
    val bubbleColor = if (isSender) {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF3B82F6), Color(0xFF9333EA))
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color(0xFF4B5563), Color(0xFF4B5563))
        )
    }

    val cornerRadius = 20.dp.toPx()
    val notchSize = 12.dp.toPx()
    val notchHeight = 8.dp.toPx()

    val path = Path().apply {
        if (isSender) {
            // Top-left corner
            moveTo(cornerRadius, 0f)

            // Top edge to top-right
            lineTo(size.width - cornerRadius, 0f)
            quadraticBezierTo(
                size.width, 0f,
                size.width, cornerRadius
            )

            // Right edge
            lineTo(size.width, size.height - cornerRadius)
            quadraticBezierTo(
                size.width, size.height,
                size.width - cornerRadius, size.height
            )

            // Bottom edge with notch on right
            lineTo(size.width - notchSize, size.height)
            lineTo(size.width, size.height + notchHeight)
            lineTo(size.width - notchSize - 4.dp.toPx(), size.height)

            // Continue bottom edge to left
            lineTo(cornerRadius, size.height)
            quadraticBezierTo(
                0f, size.height,
                0f, size.height - cornerRadius
            )

            // Left edge
            lineTo(0f, cornerRadius)
            quadraticBezierTo(
                0f, 0f,
                cornerRadius, 0f
            )
        } else {
            // Top-left corner
            moveTo(cornerRadius, 0f)

            // Top edge to top-right
            lineTo(size.width - cornerRadius, 0f)
            quadraticBezierTo(
                size.width, 0f,
                size.width, cornerRadius
            )

            // Right edge
            lineTo(size.width, size.height - cornerRadius)
            quadraticBezierTo(
                size.width, size.height,
                size.width - cornerRadius, size.height
            )

            // Bottom edge
            lineTo(notchSize + 4.dp.toPx(), size.height)

            // Bottom-left notch
            lineTo(0f, size.height + notchHeight)
            lineTo(notchSize, size.height)

            // Continue to bottom-left corner
            quadraticBezierTo(
                0f, size.height,
                0f, size.height - cornerRadius
            )

            // Left edge
            lineTo(0f, cornerRadius)
            quadraticBezierTo(
                0f, 0f,
                cornerRadius, 0f
            )
        }
        close()
    }

    drawPath(path = path, brush = bubbleColor)
}