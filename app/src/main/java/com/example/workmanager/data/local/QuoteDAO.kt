package com.example.workmanager.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.workmanager.domain.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: Quote)

    @Query("SELECT * FROM quote ORDER BY time DESC")
    fun getAllQuotes(): Flow<List<Quote>>

}