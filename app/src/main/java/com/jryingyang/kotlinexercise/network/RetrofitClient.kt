package com.jryingyang.kotlinexercise.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private const val API_DOMAIN = "https://watch-master-staging.herokuapp.com/api/"

    fun retrofitService(): ApiService {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client)
            .baseUrl(API_DOMAIN).build().create(ApiService::class.java)
    }

}