package com.example.lab2.model.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()

            val newRequest = request.newBuilder()
                .addHeader("X-Api-Key", "MHL5lFSOW5eYjENFExAqaw==8sQEaTj5zw2PPQnl")
                .build()

            chain.proceed(newRequest)
        }
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    val gson = GsonBuilder().setLenient().create()

    private val retrofit: Retrofit = Retrofit.Builder()

        .baseUrl("https://api.api-ninjas.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val instance = retrofit.create(CelebrityService::class.java)
}
