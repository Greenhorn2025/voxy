package voxy.friend.chat.common.model

import voxy.friend.chat.common.enum.MessageStatus

data class Message(
    val id: Int,
    val text: String,
    val time: String,
    val isSender: Boolean,
    val status: MessageStatus = MessageStatus.SENT,
    val dateLabel: String? = null
)
