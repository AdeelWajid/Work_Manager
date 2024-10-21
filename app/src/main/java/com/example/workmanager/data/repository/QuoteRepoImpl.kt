package com.example.workmanager.data.repository

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.workmanager.data.local.QuoteDAO
import com.example.workmanager.data.worker.FetchWorker
import com.example.workmanager.data.worker.FetchWorker.Companion.ONE_TIME_WORK_REQUEST
import com.example.workmanager.data.worker.PeriodicWorker
import com.example.workmanager.domain.model.Quote
import com.example.workmanager.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class QuoteRepoImpl(private val workManager: WorkManager, private val quoteDAO: QuoteDAO) :
    QuoteRepository {


    override fun getQuote() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(constraints)
            .build()
        workManager.enqueueUniqueWork(
            ONE_TIME_WORK_REQUEST,
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }

    override fun getAllQuotes(): Flow<List<Quote>> = quoteDAO.getAllQuotes()
    override fun setPeriodicWorkRequest() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest =
            PeriodicWorkRequest.Builder(PeriodicWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        workManager.enqueueUniquePeriodicWork(
            "periodicWorkRequest",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}