package com.matsuyami.recipy.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.matsuyami.recipy.data.local.RecipeDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

const val PREFERENCES_NAME = "recipeInfo"
val Context.dataStore: DataStore<Preferences>
                     by preferencesDataStore(name= PREFERENCES_NAME)

class RecipeInfoRepo(private val context : Context) : RecipeDataStore{
    override suspend fun putString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit{
            it[prefKey] = value
        }
    }

//    override fun getString(key: String): String? {
//        return try {
//            val prefKey = stringPreferencesKey(key)
//            val preferences = context.dataStore.data.first()
//            preferences[prefKey]
//        } catch (e : Exception){
//            e.printStackTrace()
//            null
//        }
//    }

    override suspend fun getAll() = context.dataStore.data

    override suspend fun removeString(key : String) {
        context.dataStore.edit{
            it.remove(stringPreferencesKey(key))
        }
    }

}