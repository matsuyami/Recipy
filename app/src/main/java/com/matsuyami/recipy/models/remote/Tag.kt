package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Tag(
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
): Serializable