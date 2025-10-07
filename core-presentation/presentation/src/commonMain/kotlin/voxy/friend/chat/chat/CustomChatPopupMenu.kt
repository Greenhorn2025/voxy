package voxy.friend.chat.chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.clear_chat
import voxy.core_presentation.presentation.generated.resources.delete
import voxy.core_presentation.presentation.generated.resources.disappearing
import voxy.core_presentation.presentation.generated.resources.magic_wand
import voxy.core_presentation.presentation.generated.resources.mood
import voxy.core_presentation.presentation.generated.resources.time_half_pass
import voxy.core_presentation.presentation.generated.resources.user
import voxy.core_presentation.presentation.generated.resources.your_profile
import voxy.friend.chat.common.color.AppColors

@Composable
fun CustomChatPopupMenu(
    expanded: Boolean,
    onDismiss: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onDisappearingChatsToggle: (Boolean) -> Unit = {},
    onSetMoodClick: () -> Unit = {},
    onClearChatClick: () -> Unit = {},
    disappearingChatsEnabled: Boolean = false,
    anchorAlignment: Alignment.Horizontal = Alignment.End,
    paddingFromEdge: Dp = (-12).sdp
) {
    DisposableEffect(Unit) {
        onDispose {
            onDismiss()
        }
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        offset = DpOffset(paddingFromEdge, 4.sdp),
        modifier = Modifier
            .wrapContentWidth()
            .background(
                color = Color(0xFF1F1F1F),
                shape = RoundedCornerShape(16.sdp)
            ),
        shape = RoundedCornerShape(16.sdp),
        border = BorderStroke(1.sdp, AppColors.StrokeColor),
    ){
        CustomChatMenuItem(
            icon = vectorResource(Res.drawable.user),
            iconTint = Color.White,
            title = stringResource(Res.string.your_profile),
            onClick = {
                onProfileClick()
                onDismiss()
            }
        )

        CustomChatMenuItem(
            icon = vectorResource(Res.drawable.time_half_pass),
            iconTint = Color.White,
            title = stringResource(Res.string.disappearing),
            isChecked = disappearingChatsEnabled,
            isSwitchShow = true,
            onClick = {
                onProfileClick()
                onDismiss()
            }
        )

        CustomChatMenuItem(
            icon = vectorResource(Res.drawable.magic_wand),
            iconTint = Color.White,
            title = stringResource(Res.string.mood),
            onClick = {
                onProfileClick()
                onDismiss()
            }
        )

        CustomChatMenuItem(
            icon = vectorResource(Res.drawable.delete),
            iconTint = AppColors.Delete,
            title = stringResource(Res.string.clear_chat),
            titleColor = AppColors.Delete,
            onClick = {
                onProfileClick()
                onDismiss()
            }
        )
    }
}