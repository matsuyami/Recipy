package com.recipy.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Instruction(
    @SerializedName("appliance")
    val appliance: String,
    @SerializedName("display_text")
    val displayText: String,
    @SerializedName("end_time")
    val endTime: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("position")
    val position: Int,
    @SerializedName("start_time")
    val startTime: Int,
    @SerializedName("temperature")
    val temperature: Int
): Serializable