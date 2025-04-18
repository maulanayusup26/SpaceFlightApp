package com.example.spaceflightapp.articles.model

import com.google.gson.annotations.SerializedName

data class ArticleNetwork(
    val id: Int,
    val title: String,
    val image_url: String,
    val summary: String,
    val url: String,
    val publishedAt: String,
    val newsSite: String
)

