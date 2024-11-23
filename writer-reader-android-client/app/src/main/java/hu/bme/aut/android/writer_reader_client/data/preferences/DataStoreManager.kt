package hu.bme.aut.android.writer_reader_client.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreManager {

    private const val USER_PREFERENCES_NAME = "user_preferences"

    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    val USER_TOKEN_KEY = stringPreferencesKey("user_token")
    val USER_EMAIL_KEY = stringPreferencesKey("user_email")

    suspend fun storeUserToken(context: Context, token: String) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }
    suspend fun storeUserEmail(context: Context, email: String) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    fun getUserTokenFlow(context: Context): Flow<String?> {
        return context.userPreferencesDataStore.data.map { preferences ->
            preferences[USER_TOKEN_KEY]?:""
        }
    }

    fun getUserEmailFlow(context: Context): Flow<String?> {
        return context.userPreferencesDataStore.data.map { preferences ->
            preferences[USER_EMAIL_KEY]?:""
        }
    }
}