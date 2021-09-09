package com.jryingyang.kotlinexercise.model

import com.google.gson.annotations.SerializedName

data class RequestLogin (

    @SerializedName("username")
    val userName: String,

    @SerializedName("password")
    val password: String
)