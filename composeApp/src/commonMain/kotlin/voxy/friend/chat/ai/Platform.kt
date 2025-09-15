package voxy.friend.chat.ai

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform