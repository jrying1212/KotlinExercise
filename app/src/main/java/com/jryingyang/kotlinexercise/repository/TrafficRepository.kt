package com.jryingyang.kotlinexercise.repository

import com.jryingyang.kotlinexercise.network.TrafficApiService

class TrafficRepository(private val apiService: TrafficApiService)  {

    suspend fun getTrafficList() = apiService.getTrafficList()
}