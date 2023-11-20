package com.faizul.muslimpedia.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val BASE_URL = "https://newsapi.org"
    private val API_KEY = "b59e540502a749ca9b6080605b9aa7c6"

    fun provideApiService(): ApiService{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor{chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("X-api-key", API_KEY)
                    .build()
                chain.proceed(newRequest)
            }
            .readTimeout(10, TimeUnit.SECONDS )
            .retryOnConnectionFailure(true)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}