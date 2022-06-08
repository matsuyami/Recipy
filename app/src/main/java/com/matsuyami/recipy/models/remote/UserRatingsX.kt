package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserRatingsX(
    @SerializedName("count_negative")
    val countNegative: Int,
    @SerializedName("count_positive")
    val countPositive: Int,
    @SerializedName("score")
    val score: Any
): Serializable