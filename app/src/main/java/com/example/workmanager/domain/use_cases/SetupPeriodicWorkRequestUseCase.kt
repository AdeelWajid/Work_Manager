package com.example.workmanager.domain.use_cases

import com.example.workmanager.domain.repository.QuoteRepository
import javax.inject.Inject

class SetupPeriodicWorkRequestUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {

    operator fun invoke() = quoteRepository.setPeriodicWorkRequest()
}