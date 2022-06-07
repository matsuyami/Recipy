package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Component(
    @SerializedName("extra_comment")
    val extraComment: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("ingredient")
    val ingredient: Ingredient,
    @SerializedName("measurements")
    val measurements: List<Measurement>,
    @SerializedName("position")
    val position: Int,
    @SerializedName("raw_text")
    val rawText: String
): Serializable