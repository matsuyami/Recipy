package com.matsuyami.recipy.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.matsuyami.recipy.data.repositories.RecipeInfoRepo
import com.matsuyami.recipy.viewmodels.RecipeDataVM

class FavoritesProvider(private val recipeRepo: RecipeInfoRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeDataVM(recipeRepo) as T
    }
}
