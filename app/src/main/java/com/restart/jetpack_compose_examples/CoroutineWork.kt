package com.restart.jetpack_compose_examples

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.restart.jetpack_compose_examples.ui.theme.TAG
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.job

class CoroutineWork(context: Context, private val params: WorkerParameters) :
    CoroutineWorker(context, params) {

    var index = 0

    //private val data = Data()
    override suspend fun doWork(): Result {

        Log.d(TAG, "doWork: ")
        setForeground(createForegroundInfo())

        currentCoroutineContext().job.invokeOnCompletion {
            Log.d(TAG, "doWork: Completion Worker ")
        }

        flow<Int> {
            repeat(20) {
                delay(1000)
                emit(index++)
            }
        }.collect {
            Log.d(TAG, "doWork: index = $it")
            // Send progress update to MainActivity
            setProgressAsync(workDataOf("index" to it))
        }

        Log.d(TAG, "doWork: end")
        return Result.success()
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        Log.d(TAG, "getForegroundInfo: ")
        return super.getForegroundInfo()
    }

    private fun createForegroundInfo(): ForegroundInfo {
        // Create notification channel (required for Android 8.0+)
        createNotificationChannel()

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Long Running Task")
            .setContentText("Processing...")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            //.setOngoing(true)
            .build()

        val manager = applicationContext.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        manager.notify(NOTIFICATION_ID, notification)

        return ForegroundInfo(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Long Running Tasks",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Channel for long running background tasks"
            }

            val manager = applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "long_running_task_channel"
        const val NOTIFICATION_ID = 1
    }
}
