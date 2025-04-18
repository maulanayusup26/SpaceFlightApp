package com.example.spaceflightapp.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.result.Credentials
import com.example.spaceflightapp.MainActivity
import com.example.spaceflightapp.auth.AuthManager
import com.example.spaceflightapp.databinding.ActivityLoginBinding
import com.example.spaceflightapp.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var authManager: AuthManager
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        authManager = AuthManager(this)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            loginViewModel.loginUser(email, password)

            loginViewModel.loginResult.observe(this, Observer { result ->
                when (result) {
                    is com.example.spaceflightapp.viewmodel.Result.Error -> {
                        Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    }
                    is com.example.spaceflightapp.viewmodel.Result.Success -> {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            })
        }

        binding.signupText.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}
