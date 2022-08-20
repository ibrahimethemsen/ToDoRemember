package com.ibrahimethem.todoremember.data.network.quote


import com.ibrahimethem.todoremember.domain.model.quote.Result
import retrofit2.Response
import retrofit2.http.GET

interface QuoteService {
    //random quote
    @GET("/random")
    suspend fun getRandomQuote() : Response<Result>
}