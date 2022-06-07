package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UnitX(
    @SerializedName("abbreviation")
    val abbreviation: String,
    @SerializedName("display_plural")
    val displayPlural: String,
    @SerializedName("display_singular")
    val displaySingular: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("system")
    val system: String
): Serializable