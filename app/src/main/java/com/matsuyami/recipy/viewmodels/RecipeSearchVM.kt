package com.matsuyami.recipy.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matsuyami.recipy.data.repositories.RecipeSearchRepo
import com.matsuyami.recipy.utils.Resource
import com.recipy.models.Recipes
import kotlinx.coroutines.launch
import retrofit2.Response


class RecipeSearchVM(val recipeSearchRepo : RecipeSearchRepo) : ViewModel() {
    private val recipesFrom = 0
    val recipes : MutableLiveData<Resource<Recipes>> = MutableLiveData()
    var recipesResultSize = 200

    fun getRecipes(ingredient : String) = viewModelScope.launch{
        recipes.postValue(Resource.Loading())
        val response = recipeSearchRepo.searchRecipes(ingredient, recipesFrom, recipesResultSize)
        recipes.postValue(handleRecipeResponse(response))
    }

    private fun handleRecipeResponse(resp : Response<Recipes>) : Resource<Recipes> {
        if(resp.isSuccessful){
            resp.body()?.let{ resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(resp.message())
    }
}