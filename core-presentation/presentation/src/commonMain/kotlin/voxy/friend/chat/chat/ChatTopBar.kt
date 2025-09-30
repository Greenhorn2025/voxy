package voxy.friend.chat.chat

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.ic_magic
import voxy.core_presentation.presentation.generated.resources.more
import voxy.core_presentation.presentation.generated.resources.temp_voxy1
import voxy.friend.chat.common.asyncimage.VoxyAsyncImage
import voxy.friend.chat.common.color.AppColors

@Composable
fun ChatTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        VoxyAsyncImage(
            modifier = Modifier.size(35.sdp).padding(start = 10.sdp).align(Alignment.CenterVertically)
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
            onClick = {},
            text = "Start Trial",
            enabled = true
        )

        Spacer(modifier = Modifier.width(10.sdp))

        Icon(
            modifier = Modifier.align(Alignment.CenterVertically),
            painter = painterResource(Res.drawable.ic_magic),
            contentDescription = "Magic Options",
        )

        Spacer(modifier = Modifier.width(10.sdp))

        Icon(
            modifier = Modifier.align(Alignment.CenterVertically),
            painter = painterResource(Res.drawable.more),
            contentDescription = "More Options",
        )
    }
}

