package com.example.spaceflightapp.repository

import com.example.spaceflightapp.data.ApiClient
import com.example.spaceflightapp.data.SignupRequest

class AuthRepository {
    private val api = ApiClient.auth0Api

    suspend fun signup(email: String, password: String): Boolean {
        return try {
            val request = SignupRequest(
                client_id = "0pyTSzePEyRoZE2FxDkc5FD3HgHLcOkT",
                email = email,
                password = password
            )
            val response = api.signUp(request)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

}
