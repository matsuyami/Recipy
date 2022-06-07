package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MeasurementX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("unit")
    val unit: UnitX
): Serializable