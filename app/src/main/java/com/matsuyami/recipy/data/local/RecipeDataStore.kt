package com.matsuyami.recipy.data.local

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface RecipeDataStore {
    suspend fun putString(key : String, value : String)
    suspend fun getString(key : String) : String?
    suspend fun getAll() : Flow<Preferences>
    suspend fun removeString(key : String)
}