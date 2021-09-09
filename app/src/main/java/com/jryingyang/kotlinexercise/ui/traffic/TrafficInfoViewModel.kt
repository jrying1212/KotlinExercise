package com.jryingyang.kotlinexercise.ui.traffic

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jryingyang.kotlinexercise.network.ApiResult
import com.jryingyang.kotlinexercise.repository.TrafficRepository
import kotlinx.coroutines.Dispatchers

class TrafficInfoViewModel(private val repository: TrafficRepository) : ViewModel() {
    fun getTrafficList() =
        liveData(Dispatchers.IO) {
            try {
                val result = repository.getTrafficList()

                if (result.isSuccessful) {
                    emit(ApiResult.success(data = result.body()))
                } else {
                    emit(ApiResult.error(null, "${result.code()} ${result.message()}"))
                }
            } catch (e: Exception) {
                emit(ApiResult.error(null, e.localizedMessage))
            }
        }
}