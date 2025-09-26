package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import network.chaintech.sdpcomposemultiplatform.sdp
import voxy.friend.chat.common.model.UnlockItem

@Composable
fun UnlockItemList(modifier: Modifier = Modifier, itemList: List<UnlockItem> = emptyList()) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.sdp),
        // Add this for better performance
        contentPadding = PaddingValues(horizontal = 16.sdp, vertical = 8.sdp)
    ) {
        items(count = itemList.size, key = { itemList[it].title }) {
            UnlockItemRow(modifier = modifier, item = itemList[it])
        }
    }
}