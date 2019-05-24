package com.enpassio.databindingwithnewsapikotlin.model

data class Article(
    val source: String,
    val author: String,
    val title: String,
    val description: String,
    val articleUrl: String,
    val imageUrl: String,
    val publishingDate: String,
    val content: String
)