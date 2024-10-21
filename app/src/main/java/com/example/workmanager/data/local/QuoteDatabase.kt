package com.example.workmanager.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workmanager.domain.model.Quote

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteDatabase: RoomDatabase() {

    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(context, QuoteDatabase::class.java, "quotes_db")
                .build()
    }

    abstract  fun getQuoteDao(): QuoteDAO

}