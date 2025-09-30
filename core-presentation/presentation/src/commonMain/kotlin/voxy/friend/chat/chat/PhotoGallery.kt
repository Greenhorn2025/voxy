package voxy.friend.chat.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import network.chaintech.sdpcomposemultiplatform.sdp
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.temp_voxy1
import voxy.core_presentation.presentation.generated.resources.temp_voxy2
import voxy.core_presentation.presentation.generated.resources.temp_voxy3
import voxy.core_presentation.presentation.generated.resources.temp_voxy4

@Composable
fun PhotoGallery(modifier: Modifier = Modifier) {
    val imageList = arrayListOf(
        Res.drawable.temp_voxy1,
        Res.drawable.temp_voxy2,
        Res.drawable.temp_voxy3,
        Res.drawable.temp_voxy4
    )

    LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(10.sdp)) {
        items(count = imageList.size, key = { index -> index }) {
            ChatImageItem(image = imageList[it])
        }
    }
}