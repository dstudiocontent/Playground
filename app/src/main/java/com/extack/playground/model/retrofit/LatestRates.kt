package com.extack.playground.model.retrofit

data class LatestRates(
    val base: String,
    val rates: Map<String, Double>
)