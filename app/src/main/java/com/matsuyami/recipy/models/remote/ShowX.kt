package com.recipy.models


import com.google.gson.annotations.SerializedName

data class ShowX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)