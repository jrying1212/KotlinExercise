package com.jryingyang.kotlinexercise.model.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import com.jryingyang.kotlinexercise.model.sharedPreference.ISharePreferenceManager

class SharedPreferenceManager(override val context: Context) : ISharePreferenceManager {

    private val sharedPreferenceKey = "SETTING"

    var sharedPreference: SharedPreferences =
        context.getSharedPreferences(sharedPreferenceKey, Context.MODE_PRIVATE)

    override fun putString(key: String, value: String){
        sharedPreference.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String? {
        return sharedPreference.getString(key, "")
    }

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreference.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreference.getBoolean(key, false)
    }

    override fun putInt(key: String, value: Int) {
        sharedPreference.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String): Int {
        return sharedPreference.getInt(key, 0)
    }

    override fun remove(key: String) {
        sharedPreference.edit().remove(key).apply()
    }
}