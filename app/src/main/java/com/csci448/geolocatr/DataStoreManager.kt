package com.csci448.geolocatr

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    companion object {
        private const val DATA_STORE_NAME = "Datastore"
        private val Context.dataStore: DataStore<Preferences>
                by preferencesDataStore(name = DATA_STORE_NAME)
        private val TRAFFIC = booleanPreferencesKey("isTrafficEnabled")
        private val LOCATION = booleanPreferencesKey("isLocationEnabled")
    }

    val dataFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[TRAFFIC] ?: false
    }

    suspend fun setData(newValue: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[TRAFFIC] = newValue
        }
    }

}