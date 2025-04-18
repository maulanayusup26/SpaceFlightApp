package com.example.spaceflightapp.articles.model

data class CategoryItem(
    val categoryName: String, // Nama kategori (Articles, Blogs, Reports)
    val items: List<ArticleItem> // Daftar artikel dalam kategori tersebut
)
