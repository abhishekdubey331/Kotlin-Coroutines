package com.mygatedemo.app.data.model
import com.google.gson.annotations.SerializedName


data class ApiResponse(
    @SerializedName("data")
    val data: List<ImageData> = listOf()
)

data class ImageData(
    @SerializedName("place")
    val place: String = "",
    @SerializedName("url")
    val url: String = ""
)