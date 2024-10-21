package com.example.workmanager.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workmanager.domain.model.Quote
import com.example.workmanager.domain.use_cases.GetAllQuotesFromDBUseCase
import com.example.workmanager.domain.use_cases.GetQuoteUseCase
import com.example.workmanager.domain.use_cases.SetupPeriodicWorkRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllQuotesFromDBUseCase: GetAllQuotesFromDBUseCase,
    private val getQuoteUseCase: GetQuoteUseCase,
    private val setupPeriodicWorkRequestUseCase: SetupPeriodicWorkRequestUseCase
): ViewModel() {

    val uiState = getAllQuotesFromDBUseCase.invoke().map {
        UIState(data = it)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, UIState(emptyList()))

    init {
        setupPeriodicWorkRequestUseCase.invoke()
    }

    fun getQuote() = getQuoteUseCase.invoke()

}

data class UIState(
    val data: List<Quote>
)