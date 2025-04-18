package com.example.spaceflightapp.data

data class SignupRequest(
    val client_id: String,
    val email: String,
    val password: String,
    val connection: String = "Username-Password-Authentication"
)

