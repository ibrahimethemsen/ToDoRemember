package com.ibrahimethem.todoremember.domain.model.quote

import com.squareup.moshi.Json

data class QuoteList(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "lastItemIndex")
    val lastItemIndex: Int?,
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<Result>?,
    @Json(name = "totalCount")
    val totalCount: Int?,
    @Json(name = "totalPages")
    val totalPages: Int?
)