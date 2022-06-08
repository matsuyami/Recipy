package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TotalTimeTier(
    @SerializedName("display_tier")
    val displayTier: String,
    @SerializedName("tier")
    val tier: String
): Serializable