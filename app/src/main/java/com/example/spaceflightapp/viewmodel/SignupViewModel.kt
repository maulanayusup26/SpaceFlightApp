package com.example.spaceflightapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightapp.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignupViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _signupState = MutableLiveData<Boolean>()
    val signupState: LiveData<Boolean> = _signupState

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun signupUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = repository.signup(email, password)
                if (result) {
                    _signupState.postValue(true)
                    _errorMessage.postValue(null)
                } else {
                    _signupState.postValue(false)
                    _errorMessage.postValue("Signup gagal. Cek email/password.")
                }
            } catch (e: Exception) {
                _signupState.postValue(false)
                _errorMessage.postValue(e.message ?: "Terjadi kesalahan.")
            }
        }
    }
}
