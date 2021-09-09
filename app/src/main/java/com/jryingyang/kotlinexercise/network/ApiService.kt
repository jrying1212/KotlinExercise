package com.jryingyang.kotlinexercise.network

import com.jryingyang.kotlinexercise.model.RequestLogin
import com.jryingyang.kotlinexercise.model.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun postLogin(
        @Header("X-Parse-Application-Id") id: String,
        @Body requestLogin: RequestLogin
    ): Response<ResponseLogin>

}