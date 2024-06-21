package com.ejemplo.login.service

import com.ejemplo.login.io.response.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://farma-app-dev.azurewebsites.net/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS) // Tiempo de espera para conectar
        .readTimeout(60, TimeUnit.SECONDS)    // Tiempo de espera para leer
        .writeTimeout(60, TimeUnit.SECONDS)   // Tiempo de espera para escribir
        .build()

    val instance: ApiService by lazy {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(ApiService::class.java)
    }
}

object RetrofitClientExtern {
    private const val BASE_URL = "https://santa-fe-back-dev.azurewebsites.net/"
    val instance: ApiService by lazy {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(ApiService::class.java)
    }
}
