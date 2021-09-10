package com.jryingyang.kotlinexercise.model

import com.google.gson.annotations.SerializedName

data class RequestUpdateUser(

    @SerializedName("timezone")
    val timezone: Int
)
