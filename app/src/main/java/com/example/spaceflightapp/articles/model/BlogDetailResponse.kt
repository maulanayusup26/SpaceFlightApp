package com.example.spaceflightapp.articles.model

data class BlogDetailResponse(
    val id: Int,
    val title: String,
    val summary: String,
    val published_at: String,
    val image_url: String,
    val news_site: String,
    val url: String
)

