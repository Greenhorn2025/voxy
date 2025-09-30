package voxy.friend.chat.chat

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.DrawableResource
import voxy.friend.chat.common.asyncimage.VoxyAsyncImage
import voxy.friend.chat.common.shape.AppShapes

@Composable
fun ChatImageItem(image: DrawableResource) {
    VoxyAsyncImage(
        modifier = Modifier.size(120.sdp)
            .clip(shape = AppShapes.Medium),
        contentScale = ContentScale.FillBounds,
        src = image
    )
}



