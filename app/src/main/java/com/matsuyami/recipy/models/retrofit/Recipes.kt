package com.recipy.models


import com.google.gson.annotations.SerializedName

data class Recipes(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<Result>
)