package com.extack.playground.di.modules

import com.extack.playground.constants.BASE_URL
import com.extack.playground.repo.retrofit.OpenExchangeService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun retrofit(client: OkHttpClient, jsonFactory: MoshiConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonFactory)
            .client(client)
            .build()

    @Provides
    @Singleton
    fun openExchangeService(retrofit: Retrofit): OpenExchangeService =
        retrofit.create(OpenExchangeService::class.java)

    @Singleton
    @Provides
    fun moshiFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun interceptor() = HttpLoggingInterceptor()

    @Singleton
    @Provides
    fun client(interceptor: HttpLoggingInterceptor): OkHttpClient {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }


}