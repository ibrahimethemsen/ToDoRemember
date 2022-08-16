package com.ibrahimethem.todoremember.model.quote

import com.squareup.moshi.Json

data class Result(
    @Json(name = "_id")
    val _id: String?,
    @Json(name = "author")
    val author: String?,
    @Json(name = "authorSlug")
    val authorSlug: String?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "dateAdded")
    val dateAdded: String?,
    @Json(name = "dateModified")
    val dateModified: String?,
    @Json(name = "length")
    val length: Int?,
    @Json(name = "tags")
    val tags: List<String>?
)