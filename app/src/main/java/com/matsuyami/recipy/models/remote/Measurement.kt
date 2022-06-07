package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Measurement(
    @SerializedName("id")
    val id: Int,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("unit")
    val unit: Unit
): Serializable