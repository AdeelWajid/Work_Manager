package com.example.workmanager.domain.repository

import com.example.workmanager.domain.model.Quote
import kotlinx.coroutines.flow.Flow




interface QuoteRepository {

    fun getQuote()

    fun getAllQuotes():Flow<List<Quote>>

    fun setPeriodicWorkRequest()
}