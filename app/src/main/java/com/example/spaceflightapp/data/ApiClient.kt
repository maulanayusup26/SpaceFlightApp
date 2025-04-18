package com.example.spaceflightapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://dev-fkuc7krfmyrfa8sj.us.auth0.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val auth0Api: Auth0Api by lazy {
        retrofit.create(Auth0Api::class.java)
    }
}