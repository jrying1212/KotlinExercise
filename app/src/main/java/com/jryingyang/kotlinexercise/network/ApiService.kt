package com.jryingyang.kotlinexercise.network

import com.jryingyang.kotlinexercise.model.RequestLogin
import com.jryingyang.kotlinexercise.model.RequestUpdateUser
import com.jryingyang.kotlinexercise.model.ResponseLogin
import com.jryingyang.kotlinexercise.model.ResponseUpdateUser
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("login")
    suspend fun postLogin(
        @Header("X-Parse-Application-Id") id: String,
        @Body requestLogin: RequestLogin
    ): Response<ResponseLogin>

    @PUT("users/{object}")
    suspend fun postUpdateUser(
        @Header("X-Parse-Application-Id") id: String,
        @Header("X-Parse-Session-Token") token: String,
        @Path("object") objectId: String,
        @Body requestUpdateUser: RequestUpdateUser
    ): Response<ResponseUpdateUser>

}