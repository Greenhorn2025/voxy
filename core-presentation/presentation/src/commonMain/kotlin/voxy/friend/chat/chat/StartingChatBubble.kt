package voxy.friend.chat.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import network.chaintech.sdpcomposemultiplatform.sdp
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.common.shape.AppShapes

@Composable
fun StartingChatBubble(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.sdp)
                .border(1.sdp, AppColors.StrokeColor, shape = AppShapes.Large),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = AppShapes.XXLarge,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(10.sdp)
            ) {
                PhotoGallery(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(24.sdp))
                QuoteSection()
            }
        }
    }
}