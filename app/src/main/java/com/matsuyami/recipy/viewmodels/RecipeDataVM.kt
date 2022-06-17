package com.matsuyami.recipy.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.matsuyami.recipy.data.repositories.RecipeInfoRepo
import com.recipy.models.RecipeInfo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RecipeDataVM(private val repo: RecipeInfoRepo) : ViewModel() {
    fun saveRecipeInfo(data : RecipeInfo){
        viewModelScope.launch {
            data.uuid?.let {
                repo.putString(it, Gson().toJson(data))
            }
        }
    }

    fun getRecipeInfo() {
        viewModelScope.launch {
            repo.getAll().collect { it ->
               Log.d("RecipeData", it.toString())
            }
        }
    }
}