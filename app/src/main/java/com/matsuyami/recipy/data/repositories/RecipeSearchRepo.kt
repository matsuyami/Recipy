package com.matsuyami.recipy.data.repositories

import com.matsuyami.recipy.data.network.TastyService
import com.recipy.models.Recipes
import retrofit2.Response

class RecipeSearchRepo {
   suspend fun searchRecipes(ingredientQuery: String, from : Int, size : Int) : Response<Recipes>{
      return TastyService().getRecipes(ingredientQuery, from, size)
   }
}