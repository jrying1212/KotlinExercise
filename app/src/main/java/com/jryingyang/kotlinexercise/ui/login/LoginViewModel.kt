package com.jryingyang.kotlinexercise.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.jryingyang.kotlinexercise.R
import com.jryingyang.kotlinexercise.model.ResponseError
import com.jryingyang.kotlinexercise.network.ApiResult
import com.jryingyang.kotlinexercise.repository.LoginRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun login(applicationId: String, username: String, password: String) =
        liveData(Dispatchers.IO) {
            try {
                val result = loginRepository.login(applicationId, username, password)
                if (result.isSuccessful) {
                    emit(ApiResult.success(data = result.body()))
                } else {
                    val errorResponse: ResponseError = Gson().fromJson(
                        result.errorBody()?.string(),
                        ResponseError::class.java
                    )
                    emit(ApiResult.error(data = null, errorResponse.error))
                }
            } catch (e: Exception) {
                emit(ApiResult.error(data = null, e.localizedMessage))
            }
        }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank() &&
                Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isNotBlank()
    }
}