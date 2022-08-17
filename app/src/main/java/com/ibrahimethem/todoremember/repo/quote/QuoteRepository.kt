package com.ibrahimethem.todoremember.repo.quote

import com.ibrahimethem.todoremember.base.BaseRepo
import com.ibrahimethem.todoremember.model.quote.Result
import com.ibrahimethem.todoremember.network.quote.QuoteService
import com.ibrahimethem.todoremember.util.Resource
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteService: QuoteService) : BaseRepo() {
    suspend fun getRandomQuote() : Resource<Result>{
        return safeApiCall {
            quoteService.getRandomQuote()
        }
    }
}