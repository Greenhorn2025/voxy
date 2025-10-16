package voxy.friend.chat.socket

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.header
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import voxy.friend.chat.client.HeaderProvider
import voxy.friend.chat.common.model.chat.MessageEntity
import voxy.friend.chat.common.model.chat.toDto

private const val TAG = "WebSocketManager"
class WebSocketManager(
    private val client: HttpClient,
    private val headerProvider: HeaderProvider
) : SocketManagerListener {
    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        isLenient = true
    }
    private var webSocketSession: DefaultClientWebSocketSession? = null
    private var listenerJob: Job? = null
    private var reconnectJob: Job? = null

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _connectionState = MutableStateFlow<SocketState>(SocketState.Disconnected)
    val connectionState: StateFlow<SocketState> = _connectionState.asStateFlow()

    private val _events = MutableSharedFlow<SocketEvent<String>>(replay = 0)
    val events: SharedFlow<SocketEvent<String>> = _events.asSharedFlow()

    init {
        scope.launch {
            events.collectLatest {
                when (it) {
                    is SocketEvent.Connected -> {
                        // Handle connected event if needed
                        Napier.d { "$TAG ✅ Connected" }
                    }

                    is SocketEvent.Disconnected -> {
                        // Handle disconnected event if needed
                        Napier.d { "$TAG ❌ Disconnected" }
                    }

                    is SocketEvent.Message -> {
                        Napier.d { "$TAG ❌ ${it.data}" }
                    }

                    is SocketEvent.Error -> {
                        Napier.d { "$TAG ⚠️ error : ${it.message}}" }
                        // Handle error event if needed
                    }
                }
            }
        }
    }

    private var reconnectAttempts = 0
    private val maxReconnectAttempts = 5
    private var shouldReconnect = true
    override suspend fun connect(url: String, autoReconnect: Boolean) {
        shouldReconnect = autoReconnect
        connectInternal(url)
    }

    override suspend fun connectInternal(url: String) {
        if (_connectionState.value is SocketState.Connected) {
            return
        }

        try {
            _connectionState.value = SocketState.Connecting

            webSocketSession = client.webSocketSession(urlString = url, {
                headerProvider.getHeaders().forEach { (key, value) ->
                    header(key, value)
                }
            })

            _connectionState.value = SocketState.Connected
            _events.emit(SocketEvent.Connected())
            reconnectAttempts = 0

            // Start listening to incoming messages
            listenerJob = scope.launch {
                listenToMessages()
            }

        } catch (e: Exception) {
            _connectionState.value = SocketState.Error(e.message ?: "Connection failed")
            _events.emit(SocketEvent.Error(e.message ?: "Connection failed"))

            // Auto reconnect if enabled
            if (shouldReconnect && reconnectAttempts < maxReconnectAttempts) {
                scheduleReconnect(url)
            }
        }
    }

    override suspend fun scheduleReconnect(url: String) {
        reconnectJob?.cancel()
        reconnectJob = scope.launch {
            reconnectAttempts++
            val delay = (2000 * reconnectAttempts).toLong().coerceAtMost(30000)
            delay(delay)
            connectInternal(url)
        }
    }

    override suspend fun listenToMessages() {
        try {
            webSocketSession?.incoming?.consumeAsFlow()?.collect { frame ->
                when (frame) {
                    is Frame.Text -> {
                        val text = frame.readText()
                        _events.emit(SocketEvent.Message(text))
                    }

                    is Frame.Close -> {
                        handleDisconnection()
                    }

                    else -> {}
                }
            }
        } catch (_: Exception) {
            handleDisconnection()
        }
    }

    override suspend fun handleDisconnection() {
        _connectionState.value = SocketState.Disconnected
        _events.emit(SocketEvent.Disconnected())

        // Auto reconnect if enabled
        if (shouldReconnect && reconnectAttempts < maxReconnectAttempts) {
            // Will reconnect on next connect call
        }
    }

    override suspend fun sendMessage(message: MessageEntity) {
        try {
            val messageDto = message.toDto()
            Napier.d { "${TAG} Send messageDTO: ${messageDto}" }
            val jsonMessage = json.encodeToString(messageDto)
            Napier.d { "${TAG} Send message encoded string: ${jsonMessage}" }
            webSocketSession?.send(Frame.Text(jsonMessage))
        } catch (e: Exception) {
            _events.emit(SocketEvent.Error("Failed to send message: ${e.message}"))
        }
    }

    override suspend fun disconnect() {
        shouldReconnect = false
        reconnectJob?.cancel()
        listenerJob?.cancel()

        try {
            webSocketSession?.close(CloseReason(CloseReason.Codes.NORMAL, "Client closing"))
            webSocketSession = null
            _connectionState.value = SocketState.Disconnected
            _events.emit(SocketEvent.Disconnected())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun isConnected(): Boolean {
        return _connectionState.value is SocketState.Connected
    }

    override fun cleanup() {
        scope.cancel()
    }
}