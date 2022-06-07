package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TopicX(
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
): Serializable