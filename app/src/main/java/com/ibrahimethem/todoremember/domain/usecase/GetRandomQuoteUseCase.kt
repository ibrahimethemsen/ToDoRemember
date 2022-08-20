package com.ibrahimethem.todoremember.domain.usecase

import com.ibrahimethem.todoremember.data.repository.QuoteRepository
import com.ibrahimethem.todoremember.domain.model.quote.Result
import com.ibrahimethem.todoremember.util.Resource

class GetRandomQuoteUseCase(private val quoteRepository: QuoteRepository) {
    suspend operator fun invoke() : Resource<Result>  {
        return quoteRepository.getRandomQuote()
    }
}