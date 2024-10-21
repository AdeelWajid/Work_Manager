package com.example.workmanager.data.mapper

import com.example.workmanager.data.local.QuoteDAO
import com.example.workmanager.data.model.QuoteDTO
import com.example.workmanager.domain.model.Quote

fun QuoteDTO.toDomain(workType: String): Quote{
    return Quote(
        quote = quote,
        author = author,
        id = id,
        workType = workType
    )
}