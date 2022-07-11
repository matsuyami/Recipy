package com.recipy.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Component(
    @SerializedName("id")
    val id: Int,
    @SerializedName("ingredient")
    val ingredient: Ingredient,
    @SerializedName("position")
    val position: Int,
    @SerializedName("raw_text")
    val rawText: String
) : Serializable
