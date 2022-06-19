package com.matsuyami.recipy.viewmodels

import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.matsuyami.recipy.data.repositories.RecipeInfoRepo
import com.recipy.models.RecipeInfo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeDataVM(private val repo: RecipeInfoRepo) : ViewModel() {
    var data : MutableLiveData<Preferences> = MutableLiveData()

    fun saveRecipeInfo(data : RecipeInfo){
        viewModelScope.launch {
            if(data.uuid != null){
                repo.putString(data.uuid.toString(), Gson().toJson(data))
            }
        }
    }

    fun getAll(){
        viewModelScope.launch {
            repo.getAll().collect {
                data.postValue(it)
            }
        }
    }
}