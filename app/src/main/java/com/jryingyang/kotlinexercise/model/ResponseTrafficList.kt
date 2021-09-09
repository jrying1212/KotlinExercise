package com.jryingyang.kotlinexercise.model

import com.google.gson.annotations.SerializedName

data class ResponseTrafficList(

    @SerializedName("updateTime")
    val updateTime: String,

    @SerializedName("News")
    val News: List<Traffic>
) {

    data class Traffic(
        @SerializedName("chtmessage")
        val chtmessage: String,

        @SerializedName("engmessage")
        val engmessage: String,

        @SerializedName("starttime")
        val starttime: String,

        @SerializedName("endtime")
        val endtime: String,

        @SerializedName("updatetime")
        val updatetime: String,

        @SerializedName("content")
        val content: String,

        @SerializedName("url")
        val url: String

    )

}

