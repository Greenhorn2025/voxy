package voxy.friend.chat.workmanager

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import voxy.friend.chat.common.database.dao.MessageDao
import voxy.friend.chat.common.model.chat.MessageData
import voxy.friend.chat.common.model.chat.MessageEntity
import voxy.friend.chat.socket.WebSocketManager
import java.util.concurrent.TimeUnit

class MessageSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val chatSocketService: WebSocketManager by inject()
    private val messageDao: MessageDao by inject()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            withTimeout(30000) {
                chatSocketService.connect("", true)

                // Wait for connection
                kotlinx.coroutines.delay(2000)

                // Send pending messages
                val pendingMessages = messageDao.getPendingMessages()
                pendingMessages.forEach { pending ->
                    try {
                        val message = MessageEntity(
                            id = System.currentTimeMillis().toString(),
                            data = MessageData(
                               msg = pending.text,
                                sid = pending.senderId,
                                cid = pending.timestamp
                            ),
                        )

                        chatSocketService.sendMessage(message)

                        // Save to main messages
                        messageDao.insertMessage(
                            MessageEntity(
                                id = message.id,
                                data = MessageData(
                                    msg = message.data.msg,
                                    sid = message.data.sid,
                                    rid = message.data.rid,
                                    cid = message.data.cid
                                ),
                            )
                        )

                        // Remove from pending
                        messageDao.deletePendingMessage(pending)

                    } catch (_: Exception) {
                        messageDao.incrementRetryCount(pending.localId)
                    }
                }
            }
            Result.success()
        }catch (e: Exception){
            e.printStackTrace()
            if (runAttemptCount < 3) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    companion object {
        const val WORK_NAME = "message_sync_work"

        fun schedule(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val periodicWork = PeriodicWorkRequestBuilder<MessageSyncWorker>(
                15, TimeUnit.MINUTES // Minimum interval
            )
                .setConstraints(constraints)
                .setBackoffCriteria(
                    BackoffPolicy.EXPONENTIAL,
                    10, TimeUnit.SECONDS
                )
                .build()

            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    WORK_NAME,
                    ExistingPeriodicWorkPolicy.KEEP,
                    periodicWork
                )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context)
                .cancelUniqueWork(WORK_NAME)
        }
    }
}