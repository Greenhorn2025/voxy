package voxy.friend.chat.auth

interface TrueCallerRepository {
    suspend fun authenticate(config: TruecallerConfig): TruecallerResult
    suspend fun registerForActivityResult()
    fun isAvailable(): Boolean
}