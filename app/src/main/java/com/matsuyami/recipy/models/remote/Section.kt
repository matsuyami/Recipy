package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Section(
    @SerializedName("components")
    val components: List<Component>,
    @SerializedName("name")
    val name: Any,
    @SerializedName("position")
    val position: Int
): Serializable