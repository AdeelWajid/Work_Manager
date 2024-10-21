package com.example.workmanager.data.di

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.WorkManager
import com.example.workmanager.data.local.QuoteDAO
import com.example.workmanager.data.local.QuoteDatabase
import com.example.workmanager.data.remote.ApiService
import com.example.workmanager.data.repository.QuoteRepoImpl
import com.example.workmanager.domain.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {



    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context): QuoteDatabase {
        return QuoteDatabase.getInstance(context)
    }

    @Provides
    fun provideDao(quoteDatabase: QuoteDatabase) = quoteDatabase.getQuoteDao()

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideQuoteRepository(
        quoteDAO: QuoteDAO,
        workManager: WorkManager
    ) :QuoteRepository =  QuoteRepoImpl( workManager,quoteDAO)
}