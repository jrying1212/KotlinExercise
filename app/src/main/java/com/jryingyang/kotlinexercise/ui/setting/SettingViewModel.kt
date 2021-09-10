package com.jryingyang.kotlinexercise.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.jryingyang.kotlinexercise.model.ResponseError
import com.jryingyang.kotlinexercise.network.ApiResult
import com.jryingyang.kotlinexercise.repository.SettingRepository
import kotlinx.coroutines.Dispatchers

class SettingViewModel(private val repository: SettingRepository) : ViewModel() {

    private val _selectTimeZone = MutableLiveData<Int>()
    val selectTimeZone: LiveData<Int> = _selectTimeZone

    fun updateTimeZone(
        applicationId: String,
        token: String,
        objectId: String,
        timeZone: Int
    ) =
        liveData(Dispatchers.IO) {
            try {
                val result =
                    repository.updateTimeZone(applicationId, token, objectId, timeZone)

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
                emit(ApiResult.error(null, e.localizedMessage))
            }
        }

    fun setSelectTimeZone(timeZone: Int) {
        _selectTimeZone.value = timeZone
    }
}