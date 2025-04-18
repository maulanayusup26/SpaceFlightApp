package com.example.spaceflightapp.data

import com.example.spaceflightapp.articles.model.ApiResponse
import com.example.spaceflightapp.articles.model.Article
import com.example.spaceflightapp.articles.model.ArticleItem
import com.example.spaceflightapp.articles.model.ArticleResponse
import com.example.spaceflightapp.articles.model.BlogDetailResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Auth0Api {
    @POST("dbconnections/signup")
    suspend fun signUp(@Body request: SignupRequest): Response<SignupResponse>

    @GET("articles")
    suspend fun getArticles(): Response<ArticleResponse>

    @GET("articles/{id}")
    suspend fun getArticlesId(@Path("id") id: Int): BlogDetailResponse

    @GET("blogs")
    suspend fun getBlogs(): Response<ArticleResponse>

    @GET("blogs/{id}")
    suspend fun getBlogsId(@Path("id") id: Int): BlogDetailResponse

    @GET("reports")
    suspend fun getReports(): Response<ArticleResponse>

    @GET("reports/{id}")
    suspend fun getReportsId(@Path("id") id: Int): BlogDetailResponse
}
