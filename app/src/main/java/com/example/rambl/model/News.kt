package com.example.rambl.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

@JsonClass(generateAdapter = true)
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

@JsonClass(generateAdapter = true)
data class Source(
    val id: String?,
    val name: String
)
