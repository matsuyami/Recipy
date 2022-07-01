package com.matsuyami.recipy.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.matsuyami.recipy.data.local.RecipeDataStore

const val PREFERENCES_NAME = "recipeInfo"
val Context.dataStore: DataStore<Preferences>
    by preferencesDataStore(name = PREFERENCES_NAME)

class RecipeInfoRepo(private val context: Context) : RecipeDataStore {
    override suspend fun putString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[prefKey] = value
        }
    }

    override suspend fun getAll() = context.dataStore.data

    override suspend fun removeString(key: String) {
        context.dataStore.edit {
            it.remove(stringPreferencesKey(key))
        }
    }
}
