package voxy.friend.chat.di

import io.ktor.client.HttpClient
import org.koin.dsl.module
import voxy.friend.chat.network.HttpClientFactory
import voxy.friend.chat.repository.ChatRepository
import voxy.friend.chat.repository.ChatRepositoryImpl
import voxy.friend.chat.socket.WebSocketManager

fun chatDataModule() = module{
    single<HttpClient> {
        HttpClientFactory().create()
    }
    single<WebSocketManager> { WebSocketManager(client = get(), headerProvider = get()) }
    single<ChatRepository> { ChatRepositoryImpl(webSocketManager = get(), messageDao = get()) }
}