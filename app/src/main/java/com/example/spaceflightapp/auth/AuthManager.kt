package com.example.spaceflightapp.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.example.spaceflightapp.MainActivity

class AuthManager(context: Context) {
    private val account = Auth0.getInstance("0pyTSzePEyRoZE2FxDkc5FD3HgHLcOkT", "dev-fkuc7krfmyrfa8sj.us.auth0.com")

    private val authentication = WebAuthProvider.login(account)
        .withScheme("demo")
        .withScope("openid profile email")
        .withAudience("https://dev-fkuc7krfmyrfa8sj.us.auth0.com/userinfo")

    // Fungsi untuk login
    fun login(activity: Activity, callback: Callback<Credentials, AuthenticationException>) {
        authentication.start(activity, callback)
    }

    // Fungsi untuk logout
    fun logout(context: Context, callback: (Void?, AuthenticationException?) -> Unit) {
        val activity = context as? Activity
        if (activity != null) {
            // Jika context adalah Activity, langsung lakukan logout
            WebAuthProvider.logout(account)
                .withScheme("demo")
                .start(activity, object : Callback<Void?, AuthenticationException> {
                    override fun onSuccess(result: Void?) {
                        callback(result, null)  // Panggil callback sukses
                    }

                    override fun onFailure(error: AuthenticationException) {
                        callback(null, error)
                    }
                })
        } else {
            // Jika context bukan Activity (misalnya di Service atau background), lakukan logout melalui intent
            val intent = Intent(context, MainActivity::class.java)  // Ganti dengan Activity yang sesuai
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK  // Agar bisa membuka Activity meski dari background
            context.startActivity(intent)

            // Panggil logout dengan object callback
            if (activity != null) {
                WebAuthProvider.logout(account)
                    .withScheme("demo")
                    .start(activity, object : Callback<Void?, AuthenticationException> {
                        override fun onSuccess(result: Void?) {
                            callback(result, null)
                        }

                        override fun onFailure(error: AuthenticationException) {
                            callback(null, error)
                        }
                    })
            }
        }
    }

}
