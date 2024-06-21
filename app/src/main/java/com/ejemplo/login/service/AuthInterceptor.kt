package com.example.app

import android.content.Context
import com.ejemplo.login.service.AuthService
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = AuthService.getToken(context)
        val originalRequest = chain.request()

        return if (token != null) {
            val builder = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
            val newRequest = builder.build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(originalRequest)
        }
    }
}
