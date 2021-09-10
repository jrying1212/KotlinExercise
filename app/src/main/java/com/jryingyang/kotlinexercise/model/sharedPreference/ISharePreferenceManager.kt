package com.jryingyang.kotlinexercise.model.sharedPreference

import android.content.Context

interface ISharePreferenceManager {
    val context: Context

    fun putString(key: String, value: String)

    fun getString(key: String): String?

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String): Boolean

    fun putInt(key: String, value: Int)

    fun getInt(key: String): Int

    fun remove(key: String)
}