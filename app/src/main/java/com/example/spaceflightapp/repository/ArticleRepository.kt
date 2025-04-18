package com.example.spaceflightapp.repository

import com.example.spaceflightapp.articles.model.ArticleItem
import com.example.spaceflightapp.articles.model.CategoryItem
import com.example.spaceflightapp.data.Auth0Api

class ArticleRepository(private val api: Auth0Api) {

    suspend fun fetchAllCategories(): List<CategoryItem> {
        val categories = mutableListOf<CategoryItem>()

        val articleResponse = api.getArticles()
        if (articleResponse.isSuccessful) {
            val articles = articleResponse.body()?.results?.map {
                ArticleItem(it.id,it.title,it.image_url,it.summary,it.url,it.publishedAt,it.newsSite)
            } ?: emptyList()
            categories.add(CategoryItem("Articles", articles))
        }

        val blogResponse = api.getBlogs()
        if (blogResponse.isSuccessful) {
            val blogs = blogResponse.body()?.results?.map {
                ArticleItem(it.id,it.title,it.image_url,it.summary,it.url,it.publishedAt,it.newsSite)
            } ?: emptyList()
            categories.add(CategoryItem("Blogs", blogs))
        }

        val reportResponse = api.getReports()
        if (reportResponse.isSuccessful) {
            val reports = reportResponse.body()?.results?.map {
                ArticleItem(it.id,it.title,it.image_url,it.summary,it.url,it.publishedAt,it.newsSite)
            } ?: emptyList()
            categories.add(CategoryItem("Reports", reports))
        }

        return categories
    }
}




