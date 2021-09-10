package com.jryingyang.kotlinexercise.model

import com.google.gson.annotations.SerializedName

data class ResponseUpdateUser(

    @SerializedName("updatedAt")
    val updatedAt: String

)
