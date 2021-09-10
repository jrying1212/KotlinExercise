package com.jryingyang.kotlinexercise.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseLogin(
    @SerializedName("username")
    val username: String,

    @SerializedName("reportEmail")
    val reportEmail: String,

    @SerializedName("timezone")
    val timezone: Int,

    @SerializedName("objectId")
    val objectId: String,

    @SerializedName("sessionToken")
    val sessionToken: String

): Parcelable

data class ResponseError(
    @SerializedName("code")
    val code: Int,

    @SerializedName("error")
    val error: String
)
