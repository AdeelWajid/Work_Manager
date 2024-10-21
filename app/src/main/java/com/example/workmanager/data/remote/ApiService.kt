package com.example.workmanager.data.remote

import com.example.workmanager.data.model.QuoteDTO
import retrofit2.http.GET

interface ApiService {

//    https://dummyjson.com/quotes/random
    @GET("quotes/random")
    suspend fun getRandomQuote(): QuoteDTO

}