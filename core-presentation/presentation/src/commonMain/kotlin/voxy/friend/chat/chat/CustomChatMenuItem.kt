package voxy.friend.chat.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.common.extension.DisplayIf

@Composable
fun CustomChatMenuItem(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    titleColor: Color = Color.White,
    isChecked: Boolean = false,
    isSwitchShow: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 8.sdp, vertical = 12.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.Top,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(16.sdp)
            )

            Spacer(modifier = Modifier.width(8.sdp))

            Column {
                Text(
                    text = title,
                    color = titleColor,
                    style = MaterialTheme.typography.labelLarge,
                )
                DisplayIf(isSwitchShow) {
                    Spacer(modifier = Modifier.height(3.sdp))
                    Text(
                        modifier = Modifier.width(120.sdp),
                        text = "Any chat after this is turned on will disappear after 24 hours",
                        color = AppColors.disableColor,
                        maxLines = 2,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 8.ssp,
                            lineHeight = 10.ssp
                        ),
                    )
                }
            }
        }

        DisplayIf(isSwitchShow) {
            Spacer(modifier = Modifier.width(12.sdp))
            Switch(
                checked = isChecked,
                onCheckedChange = { },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF34C759),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Gray
                )
            )
        }
    }
}