package com.example.workmanager.domain.use_cases

import com.example.workmanager.domain.model.Quote
import com.example.workmanager.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllQuotesFromDBUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository,
) {
    operator fun invoke() = quoteRepository.getAllQuotes()
}