package com.jryingyang.kotlinexercise.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jryingyang.kotlinexercise.model.sharedPreference.SharedPreferenceManager
import com.jryingyang.kotlinexercise.network.RetrofitClient
import com.jryingyang.kotlinexercise.repository.LoginRepository

class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    RetrofitClient.retrofitService()
                ), SharedPreferenceManager(context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}