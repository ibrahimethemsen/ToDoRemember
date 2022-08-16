package com.ibrahimethem.todoremember.network.quote

import com.ibrahimethem.todoremember.model.quote.QuoteList
import com.ibrahimethem.todoremember.model.quote.Result
import retrofit2.Response
import retrofit2.http.GET

interface QuoteService {
    //random quote
    @GET("/random")
    suspend fun getRandomQuote() : Response<Result>
}