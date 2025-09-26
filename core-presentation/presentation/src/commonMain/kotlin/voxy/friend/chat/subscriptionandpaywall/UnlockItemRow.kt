package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.painterResource
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.google_icon
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.common.model.UnlockItem

@Composable
fun UnlockItemRow(modifier: Modifier = Modifier, item: UnlockItem) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(Res.drawable.google_icon),
            modifier = Modifier.size(24.sdp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(12.sdp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(4.sdp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = AppColors.disableColor
            )
        }
    }
}