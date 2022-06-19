package com.matsuyami.recipy.data.local

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RecipeDataStore {
    suspend fun putString(key : String, value : String)
//    fun getString(key : String) : String?
    suspend fun getAll() : Flow<Preferences>
    suspend fun removeString(key : String)
}