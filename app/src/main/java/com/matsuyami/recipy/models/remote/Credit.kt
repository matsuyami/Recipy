package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Credit(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
): Serializable