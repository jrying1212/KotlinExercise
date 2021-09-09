package com.jryingyang.kotlinexercise.network

import com.jryingyang.kotlinexercise.model.ResponseTrafficList
import retrofit2.Response
import retrofit2.http.GET

interface TrafficApiService {

    @GET("dotapp/news.json")
    suspend fun getTrafficList(): Response<ResponseTrafficList>

}