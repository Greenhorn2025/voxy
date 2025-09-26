package voxy.friend.chat.common.model

import org.jetbrains.compose.resources.DrawableResource

data class UnlockItem(
    val icon: DrawableResource ?= null,
    val title: String,
    val description: String
)
