package voxy.friend.chat.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.ic_magic
import voxy.core_presentation.presentation.generated.resources.internet_issue
import voxy.core_presentation.presentation.generated.resources.more
import voxy.core_presentation.presentation.generated.resources.temp_voxy1
import voxy.friend.chat.common.asyncimage.VoxyAsyncImage
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.common.extension.DisplayIf
import voxy.friend.chat.network.NetworkMonitor
import voxy.friend.chat.network.NetworkState

@Composable
fun ChatTopBar(
    modifier: Modifier = Modifier,
    showMoreMenu: Boolean,
    onMoreMenuClick: () -> Unit,
    onDismissMenu: () -> Unit,
    onProfileClick: () -> Unit,
    onDisappearingChatsToggle: (Boolean) -> Unit,
    onSetMoodClick: () -> Unit,
    onClearChatClick: () -> Unit,
    onStartTrialClick: () -> Unit,
    disappearingChatsEnabled: Boolean = false
) {
    Column(modifier = modifier) {
        val monitor: NetworkMonitor = koinInject<NetworkMonitor>()
        val networkState by monitor.networkState.collectAsStateWithLifecycle()

        Row(
            modifier = modifier
        ) {
            VoxyAsyncImage(
                modifier = Modifier.size(35.sdp).padding(start = 10.sdp)
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(50)),
                src = Res.drawable.temp_voxy1,
                contentDescription = "App Logo",
            )

            Spacer(modifier = Modifier.width(15.sdp))

            Column(modifier = Modifier.wrapContentWidth()) {
                Text(
                    text = "Voxy",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.ssp),
                    color = AppColors.white
                )

                Spacer(modifier = Modifier.height(4.sdp))

                OnlineStatusIndicator(
                    isOnline = true,
                    text = "Online",
                    textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 10.ssp),
                    modifier = Modifier
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            StartTrialButton(
                modifier = Modifier.wrapContentWidth(),
                onClick = {
                    onStartTrialClick.invoke()
                },
                text = "Start Trial",
                enabled = true
            )

            Spacer(modifier = Modifier.width(10.sdp))

            Icon(
                modifier = Modifier.align(Alignment.CenterVertically).clickable {
                    onMoreMenuClick()
                },
                painter = painterResource(Res.drawable.ic_magic),
                contentDescription = "Magic Options",
            )

            Spacer(modifier = Modifier.width(10.sdp))

            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                IconButton(onClick = onMoreMenuClick) {
                    Icon(
                        painter = painterResource(Res.drawable.more),
                        contentDescription = "More Options",
                    )
                }

                CustomChatPopupMenu(
                    expanded = showMoreMenu,
                    onDismiss = onDismissMenu,
                    onProfileClick = onProfileClick,
                    onDisappearingChatsToggle = onDisappearingChatsToggle,
                    onSetMoodClick = onSetMoodClick,
                    onClearChatClick = onClearChatClick,
                    disappearingChatsEnabled = disappearingChatsEnabled,
                )
            }
        }

        Spacer(modifier = Modifier.height(10.sdp))

        DisposableEffect(Unit) {
            monitor.startMonitoring()
            onDispose { monitor.stopMonitoring() }
        }

        DisplayIf(visible = networkState.state == NetworkState.DISCONNECTED && !networkState.hasInternetAccess) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(Res.string.internet_issue),
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.ssp),
                color = AppColors.disableColor_1
            )
        }

    }

}

