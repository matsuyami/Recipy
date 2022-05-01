package com.matsuyami.recipy.api

import com.matsuyami.recipy.BuildConfig
import com.recipy.models.Recipes
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TastyService {
    @Headers(
        "X-RapidAPI-Host:tasty.p.rapidapi.com",
        "X-RapidAPI-Key:${BuildConfig.TASTY_KEY}"
    )
    @GET("recipes/list")
    fun getRecipes(@Query("q") query : String?,
                   @Query("from") from : Int,
                   @Query("size") size : Int) : Call<Recipes>
}