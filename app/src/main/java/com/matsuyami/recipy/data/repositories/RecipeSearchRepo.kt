package com.matsuyami.recipy.data.repositories

import com.matsuyami.recipy.data.network.TastyService
import com.recipy.models.Recipes
import retrofit2.Response
import javax.inject.Inject

class RecipeSearchRepo @Inject constructor(private val service: TastyService) {
    suspend fun searchRecipes(ingredientQuery: String, from: Int, size: Int): Response<Recipes> {
        return service.getRecipes(ingredientQuery, from, size)
    }
}
