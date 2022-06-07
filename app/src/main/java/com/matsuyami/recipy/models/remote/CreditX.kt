package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CreditX(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
): Serializable