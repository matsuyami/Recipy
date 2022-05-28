package com.recipy.models


import com.google.gson.annotations.SerializedName

data class Measurement(
    @SerializedName("id")
    val id: Int,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("unit")
    val unit: Unit
)