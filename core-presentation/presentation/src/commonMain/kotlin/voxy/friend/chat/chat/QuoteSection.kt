package voxy.friend.chat.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun QuoteSection() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "I love festivals, traditions, and homely conversations. With me, you'll always feel cared for and understood 🌻",
            color = Color.White,
            fontSize = 18.sp,
            lineHeight = 28.sp,
            modifier = Modifier.wrapContentWidth()
        )
    }
}