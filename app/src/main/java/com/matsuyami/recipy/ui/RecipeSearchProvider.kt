package com.matsuyami.recipy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.matsuyami.recipy.data.repositories.RecipeSearchRepo
import com.matsuyami.recipy.viewmodels.RecipeVM

class RecipeSearchProvider(val recipeRepo : RecipeSearchRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeVM(recipeRepo) as T
    }
}