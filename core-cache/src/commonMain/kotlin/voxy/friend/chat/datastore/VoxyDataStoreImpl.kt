package voxy.friend.chat.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath
import voxy.friend.chat.interfaces.VoxyDataStore

internal const val dataStoreFileName = "voxy.preferences_pb"

fun initDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )


class VoxyDataStoreImpl(
    private val dataStore: DataStore<Preferences>,
): VoxyDataStore {

    companion object Companion {
        val USER_LOGGED_IN_KEY = booleanPreferencesKey("is_user_logged_in")
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("is_notifications_enabled")
    }

    val readIsUserLoggedInState: Flow<Boolean> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {
                it[USER_LOGGED_IN_KEY] ?: false
            }

    suspend fun saveIsUserLoggedIn(exist: Boolean) {
        dataStore.edit {
            it[USER_LOGGED_IN_KEY] = exist
        }
    }

    val readNotificationPreference: Flow<Boolean> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {
                it[NOTIFICATIONS_ENABLED] ?: true
            }

    suspend fun saveNotificationPreference(enabled: Boolean) {
        dataStore.edit {
            it[NOTIFICATIONS_ENABLED] = enabled
        }
    }

    override suspend fun saveObjectInternal(key: String, jsonString: String) {
        dataStore.edit { it[stringPreferencesKey(key)] = jsonString }
    }

    override fun getObjectInternal(key: String): Flow<String?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[stringPreferencesKey(key)]
            }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    override suspend fun remove(key: String) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key))
            preferences.remove(booleanPreferencesKey(key))
        }
    }
}