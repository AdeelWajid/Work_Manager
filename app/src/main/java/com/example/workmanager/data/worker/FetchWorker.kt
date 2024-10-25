package com.example.workmanager.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanager.data.local.QuoteDAO
import com.example.workmanager.data.mapper.toDomain
import com.example.workmanager.data.remote.ApiService
import com.example.workmanager.data.worker.factory.Test
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workParams: WorkerParameters,
    private val apiService: ApiService,
    private val quoteDAO: QuoteDAO
) : CoroutineWorker(context, workParams) {
    companion object {
        const val ONE_TIME_WORK_REQUEST = "ONE_TIME_WORK_REQUEST"
    }

    override suspend fun doWork(): Result {
        return try {

            val response = apiService.getRandomQuote().toDomain(ONE_TIME_WORK_REQUEST)
            quoteDAO.insert(response)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}