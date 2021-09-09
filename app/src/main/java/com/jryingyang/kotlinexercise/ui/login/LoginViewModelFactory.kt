package com.jryingyang.kotlinexercise.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jryingyang.kotlinexercise.network.RetrofitClient
import com.jryingyang.kotlinexercise.repository.LoginRepository

class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    RetrofitClient.retrofitService()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}