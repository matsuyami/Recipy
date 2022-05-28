package com.recipy.models


import com.google.gson.annotations.SerializedName

data class SectionX(
    @SerializedName("components")
    val components: List<ComponentX>,
    @SerializedName("name")
    val name: Any,
    @SerializedName("position")
    val position: Int
)