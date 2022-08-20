package com.ibrahimethem.todoremember.data.networkdatasource

import com.ibrahimethem.todoremember.data.network.quote.QuoteService
import com.ibrahimethem.todoremember.domain.model.quote.Result
import retrofit2.Response

class QuoteNetworkDataSource(private val quoteService: QuoteService) {
    suspend fun getRandomQuote() : Response<Result> {
        return quoteService.getRandomQuote()
    }
}