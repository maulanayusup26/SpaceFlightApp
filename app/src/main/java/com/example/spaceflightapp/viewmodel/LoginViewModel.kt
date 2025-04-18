package com.example.spaceflightapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Result<Any?>>()
    val loginResult: LiveData<Result<Any?>> get() = _loginResult

    fun loginUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _loginResult.value = Result.Error("Email and password cannot be empty")
            return
        }

        try {
            _loginResult.value = Result.Success("Login successful")
        } catch (exception: Exception) {
            _loginResult.value = Result.Error("An error occurred: ${exception.message}")
        }
    }
}

sealed class Result<T> {
    data class Success(val message: String) : Result<Any?>()
    data class Error(val message: String) : Result<Any?>()
}
