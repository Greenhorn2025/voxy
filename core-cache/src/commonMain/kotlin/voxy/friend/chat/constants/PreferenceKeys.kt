package voxy.friend.chat.constants

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    const val SIGNUP_USER_DTO = "sign_up_user_dto"
    const val LOGGED_IN_USER_DTO = "logged_in_user_dto"
    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    val TOKEN_EXPIRY = stringPreferencesKey("token_expiry")
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    // User Data Keys
    val USER_ID = stringPreferencesKey("user_id")
    val USER_NAME = stringPreferencesKey("user_name")
    val USER_EMAIL = stringPreferencesKey("user_email")
    val USER_PHONE = stringPreferencesKey("user_phone")
    val USER_AVATAR = stringPreferencesKey("user_avatar")
}