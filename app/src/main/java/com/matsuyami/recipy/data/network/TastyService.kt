package com.matsuyami.recipy.data.network

import com.matsuyami.recipy.BuildConfig
import com.recipy.models.Recipes
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TastyService {
    @Headers(
        "X-RapidAPI-Host:tasty.p.rapidapi.com",
        "X-RapidAPI-Key:${BuildConfig.TASTY_KEY}"
    )
    @GET("recipes/list")
    suspend fun getRecipes(@Query("q") query : String?,
                   @Query("from") from : Int = 0,
                   @Query("size") size : Int = 200) : Response<Recipes>

    companion object{
        operator fun invoke() : TastyService {
            return Retrofit.Builder()
                .baseUrl("https://tasty.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(TastyService::class.java)
        }
    }
}