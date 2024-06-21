package com.example.app

import android.content.Context
import com.ejemplo.login.io.response.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://farma-app-dev.azurewebsites.net/"

    fun getInstance(context: Context): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS) // Tiempo de espera para conectar
            .readTimeout(60, TimeUnit.SECONDS)    // Tiempo de espera para leer
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}

object RetrofitClientRegister {
    private const val BASE_URL = "https://farma-app-dev.azurewebsites.net/"
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

object RetrofitClientExtern {
    private const val BASE_URL = "https://santa-fe-back-dev.azurewebsites.net/"
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
