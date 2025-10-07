package voxy.friend.chat.emoji

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Sports
import androidx.compose.ui.graphics.vector.ImageVector

enum class EmojiCategory(val icon: ImageVector, val label: String) {
    RECENT(Icons.Default.AccessTime, "Recent"),
    SMILEYS(Icons.Default.EmojiEmotions, "Smileys"),
    ANIMALS(Icons.Default.Pets, "Animals"),
    FOOD(Icons.Default.Fastfood, "Food"),
    ACTIVITIES(Icons.Default.Sports, "Activities"),
    TRAVEL(Icons.Default.Flight, "Travel"),
    OBJECTS(Icons.Default.Lightbulb, "Objects"),
    SYMBOLS(Icons.Default.Favorite, "Symbols"),
    FLAGS(Icons.Default.Flag, "Flags")
}
