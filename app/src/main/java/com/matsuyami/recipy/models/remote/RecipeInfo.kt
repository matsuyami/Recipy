package com.recipy.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipeInfo(
    var uuid: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("instructions")
    val instructions: List<Instruction>,
    @SerializedName("name")
    val name: String,
    @SerializedName("sections")
    val sections: List<Section>,
    @SerializedName("thumbnail_alt_text")
    val thumbnailAltText: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
) : Serializable
