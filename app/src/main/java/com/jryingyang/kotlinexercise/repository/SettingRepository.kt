package com.jryingyang.kotlinexercise.repository

import com.jryingyang.kotlinexercise.model.RequestUpdateUser
import com.jryingyang.kotlinexercise.network.ApiService

class SettingRepository(private val apiService: ApiService) {

    suspend fun updateTimeZone(
        applicationId: String,
        token: String,
        objectId: String,
        timeZone: Int
    ) = apiService.postUpdateUser(
        applicationId,
        token,
        objectId,
        RequestUpdateUser(timeZone)
    )
}