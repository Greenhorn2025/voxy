package voxy.friend.chat.common.model.chat

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import voxy.friend.chat.common.enum.MessageStatus

@Serializable
data class MessageDto(
    val id: String,
    val action: String = "SEND_MESSAGE",
    val data: MessageDataDto
)

@Serializable
data class MessageDataDto(
    val msg: String,
    val sid: Long,
    val rid: Long? = null,
    val cid: Long,
    val isSender: Boolean = false,
    val sts: String = "SENT"
)

fun MessageEntity.toDto(): MessageDto {
    return MessageDto(
        id = id,
        action = action,
        data = MessageDataDto(
            msg = data.msg,
            sid = data.sid,
            rid = data.rid,
            cid = data.cid,
            isSender = data.isSender,
            sts = data.sts.name
        )
    )
}

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey
    val id: String,
    val action : String = "SEND_MESSAGE",
    @Embedded
    val data : MessageData
)

data class MessageData(
    val msg: String,
    val sid: Long,
    val rid: Long? = null,
    val cid: Long,
    val isSender : Boolean = false,
    val sts : MessageStatus = MessageStatus.SENT
)