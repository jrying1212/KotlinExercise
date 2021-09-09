package com.jryingyang.kotlinexercise.model

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("username")
    val username: String,

    @SerializedName("isVerifiedReportEmail")
    val isVerifiedReportEmail:Boolean,

    @SerializedName("reportEmail")
    val reportEmail: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("objectId")
    val objectId: String,

    @SerializedName("sessionToken")
    val sessionToken: String,

    @SerializedName("code")
    val code: String,
)

data class ResponseError(
    @SerializedName("code")
    val code: Int,

    @SerializedName("error")
    val error: String
)
