package voxy.friend.chat.emoji

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmojiBottomSheet(
    onEmojiSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selectedCategory by remember { mutableStateOf(EmojiCategory.SMILEYS) }
    var recentEmojis by remember { mutableStateOf(listOf<String>()) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color(0xFF1F1F1F),
        contentColor = Color.White,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            ScrollableTabRow(
                selectedTabIndex = EmojiCategory.values().indexOf(selectedCategory),
                modifier = Modifier.fillMaxWidth(),
                containerColor = Color(0xFF1F1F1F),
                contentColor = Color.White,
                indicator = {},
                divider = {}
            ) {
                EmojiCategory.values().forEach { category ->
                    Tab(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = category.icon,
                            contentDescription = category.label,
                            tint = if (selectedCategory == category)
                                Color(0xFF667EEA)
                            else
                                Color.Gray,
                            modifier = Modifier
                                .size(28.dp)
                                .padding(4.dp)
                        )
                    }
                }
            }

            Divider(color = Color.Gray.copy(alpha = 0.2f))

            val emojis = when (selectedCategory) {
                EmojiCategory.RECENT -> recentEmojis
                EmojiCategory.SMILEYS -> EmojiData.smileys
                EmojiCategory.ANIMALS -> EmojiData.animals
                EmojiCategory.FOOD -> EmojiData.food
                EmojiCategory.ACTIVITIES -> EmojiData.activities
                EmojiCategory.TRAVEL -> EmojiData.travel
                EmojiCategory.OBJECTS -> EmojiData.objects
                EmojiCategory.SYMBOLS -> EmojiData.symbols
                EmojiCategory.FLAGS -> EmojiData.flags
            }

            if (emojis.isEmpty() && selectedCategory == EmojiCategory.RECENT) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No recent emojis",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 48.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    items(emojis) { emoji ->
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .clickable {
                                    onEmojiSelected(emoji)
                                    // Add to recent
                                    if (!recentEmojis.contains(emoji)) {
                                        recentEmojis = listOf(emoji) + recentEmojis.take(39)
                                    }
                                }
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = emoji,
                                fontSize = 28.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}