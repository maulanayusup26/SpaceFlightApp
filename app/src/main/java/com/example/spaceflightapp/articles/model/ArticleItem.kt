package com.example.spaceflightapp.articles.model

data class ArticleItem(
    val id: Int,
    val title: String,
    val image_url: String,
    val summary: String,
    val url: String,
    val publishedAt: String?,
    val newsSite: String?
)
