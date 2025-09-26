package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.common.model.UnlockItem

@Composable

fun PaywallUnlockSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "- UNLOCK -",
            color = AppColors.yellow_bright,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        )

        Spacer(Modifier.height(10.dp))

        UnlockItemList(
            modifier = Modifier.fillMaxWidth(),
            itemList = getUnlockItems()
        )
    }
}

private fun getUnlockItems(): List<UnlockItem> = listOf(
    UnlockItem(
        title = "Unlimited Chats",
        description = "Talk as much as you want, no limits."
    ),
    UnlockItem(
        title = "Set Different Moods",
        description = "Funny, romantic, filmy & more"
    ),
    UnlockItem(
        title = "Disappearing Chats",
        description = "Your conversations, your choice."
    )
)