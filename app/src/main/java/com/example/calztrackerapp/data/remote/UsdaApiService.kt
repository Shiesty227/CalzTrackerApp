package com.example.calztrackerapp.data.remote

import com.example.calztrackerapp.BuildConfig

import com.example.calztrackerapp.data.remote.model.UsdaSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UsdaApiService {

    @GET("foods/search")
    suspend fun searchFoods(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.USDA_API_KEY
    ): UsdaSearchResponse
}
