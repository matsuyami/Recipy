package com.recipy.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Instruction(
    @SerializedName("display_text")
    val displayText: String,
    @SerializedName("id")
    val id: Int,
) : Serializable
