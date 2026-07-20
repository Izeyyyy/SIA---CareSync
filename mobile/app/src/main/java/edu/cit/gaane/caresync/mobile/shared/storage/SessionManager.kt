package edu.cit.gaane.caresync.mobile.shared.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(
    name = "caresync_session"
)


class SessionManager(
    private val context: Context
) {


    companion object {

        private val TOKEN =
            stringPreferencesKey("jwt_token")

        private val USER_ROLE =
            stringPreferencesKey("user_role")

        private val USER_EMAIL =
            stringPreferencesKey("user_email")

        private val FIRST_NAME =
            stringPreferencesKey("first_name")

    }



    suspend fun saveSession(
        token: String,
        role: String,
        email: String,
        firstName: String
    ) {

        context.dataStore.edit { preferences ->

            preferences[TOKEN] = token
            preferences[USER_ROLE] = role
            preferences[USER_EMAIL] = email
            preferences[FIRST_NAME] = firstName

        }

    }



    val token: Flow<String?> =
        context.dataStore.data.map {

                preferences ->

            preferences[TOKEN]

        }



    suspend fun clearSession() {

        context.dataStore.edit {

            it.clear()

        }

    }

}