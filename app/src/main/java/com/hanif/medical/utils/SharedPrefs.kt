package com.hanif.medical.utils

import android.content.Context
import android.content.SharedPreferences

const val USER_TOKEN = "user_token"
const val EMAIL_TOKEN = "email_token"
const val PASSWORD_TOKEN = "password_token"
const val PREF = "MyAppPrefName"
const val LOGIN_PREF = "LOGIN"
const val ON_BOARDING_PREF = "LOGIN"

@Suppress("UNCHECKED_CAST")
class SharedPrefs(context: Context) {

    fun getOnBoardingSF(context: Context): SharedPreferences {
        return context.getSharedPreferences(ON_BOARDING_PREF, Context.MODE_PRIVATE)
    }

    fun getLoginSF(context: Context): SharedPreferences {
        return context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE)
    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun setString(prefsFile: String, token: String?) {
        put(prefsFile, token)
    }

    fun getString(prefsFile: String): String {
        return get(prefsFile, String::class.java)
    }

    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    fun getValue(key: String): String? {
        return sharedPref.getString(key, "")
    }

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear(prefsFile: String) {
        sharedPref.edit().run {
            remove(prefsFile)
        }.apply()
    }

   /* fun logoutSharePref(context: Context) {
        sharedPref.edit().clear().apply()
        getNotificationSF(context).edit().clear().apply()
    }*/
}