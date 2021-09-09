package com.jryingyang.kotlinexercise.repository

import com.jryingyang.kotlinexercise.model.RequestLogin
import com.jryingyang.kotlinexercise.network.ApiService

class LoginRepository(private val apiService: ApiService) {

    suspend fun login(applicationId: String, username: String, password: String) = apiService.postLogin(
        applicationId,
        RequestLogin(username, password)
    )
}