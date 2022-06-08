package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NutritionX(
    @SerializedName("calories")
    val calories: Int,
    @SerializedName("carbohydrates")
    val carbohydrates: Int,
    @SerializedName("fat")
    val fat: Int,
    @SerializedName("fiber")
    val fiber: Int,
    @SerializedName("protein")
    val protein: Int,
    @SerializedName("sugar")
    val sugar: Int,
    @SerializedName("updated_at")
    val updatedAt: String
): Serializable