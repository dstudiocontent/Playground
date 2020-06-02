package com.extack.playground.repo.retrofit

import com.extack.playground.model.retrofit.LatestRates
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenExchangeService {
    @GET("/latest.json")
    suspend fun getLatestCurrencies(@Query("app_id") appId: String): LatestRates
}