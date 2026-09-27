package com.example.calztrackerapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UsdaApi {

    // ✅ Logging interceptor for debug
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // ✅ OkHttpClient with logging
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    // ✅ Retrofit instance
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nal.usda.gov/fdc/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service: UsdaApiService = retrofit.create(UsdaApiService::class.java)
}
