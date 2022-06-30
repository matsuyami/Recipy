package com.matsuyami.recipy.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matsuyami.recipy.data.repositories.RecipeSearchRepo
import com.matsuyami.recipy.utils.Resource
import com.recipy.models.Recipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RecipeVM @Inject constructor(
        private val recipeSearchRepo : RecipeSearchRepo
    ) : ViewModel() {

    val recipes : MutableLiveData<Resource<Recipes>> = MutableLiveData()

    fun getRecipes(ingredient : String) = viewModelScope.launch{
        val recipesFrom = 0
        val recipesResultSize = 200
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
