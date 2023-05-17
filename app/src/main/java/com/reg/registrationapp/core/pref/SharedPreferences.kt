package com.reg.registrationapp.core.pref

import android.content.Context
import javax.inject.Inject

private const val GET_AUTH_TOKEN = "Auth_token"
private const val GET_REFRESH_TOKEN = "refreshed_token"

class SharedPreferences @Inject constructor(private val context: Context) {

    private val tokenStorage = "token_storage"

    fun getAuthToken() =
        context.getSharedPreferences(tokenStorage, Context.MODE_PRIVATE)
            .getString(GET_AUTH_TOKEN, null)

    fun getRefreshedToken () =
        context.getSharedPreferences(tokenStorage, Context.MODE_PRIVATE)
            .getString(GET_REFRESH_TOKEN, null)

    fun saveAuthToken(token:String) =
        context.getSharedPreferences(tokenStorage, Context.MODE_PRIVATE)
            .edit()
            .putString(GET_AUTH_TOKEN, token)
            .apply()

    fun saveRefreshedToken(token: String) =
        context.getSharedPreferences(tokenStorage, Context.MODE_PRIVATE)
            .edit()
            .putString(GET_REFRESH_TOKEN, token)
            .apply()

}