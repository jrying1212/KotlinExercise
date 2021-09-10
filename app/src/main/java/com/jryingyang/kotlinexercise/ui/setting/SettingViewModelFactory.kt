package com.jryingyang.kotlinexercise.ui.setting

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jryingyang.kotlinexercise.model.sharedPreference.SharedPreferenceManager
import com.jryingyang.kotlinexercise.network.RetrofitClient
import com.jryingyang.kotlinexercise.repository.SettingRepository

class SettingViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(
                repository = SettingRepository(
                    RetrofitClient.retrofitService(),
                ),  SharedPreferenceManager(context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}