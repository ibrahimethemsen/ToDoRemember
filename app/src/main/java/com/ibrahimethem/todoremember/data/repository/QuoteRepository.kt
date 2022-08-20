package com.ibrahimethem.todoremember.data.repository

import com.ibrahimethem.todoremember.base.BaseRepo
import com.ibrahimethem.todoremember.domain.model.quote.Result
import com.ibrahimethem.todoremember.data.networkdatasource.QuoteNetworkDataSource
import com.ibrahimethem.todoremember.util.Resource
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteNetworkDataSource: QuoteNetworkDataSource) : BaseRepo() {
    suspend fun getRandomQuote() : Resource<Result>{
        return safeApiCall {
            quoteNetworkDataSource.getRandomQuote()
        }
    }
}