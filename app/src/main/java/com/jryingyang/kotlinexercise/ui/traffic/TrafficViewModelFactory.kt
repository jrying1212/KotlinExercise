package com.jryingyang.kotlinexercise.ui.traffic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jryingyang.kotlinexercise.network.RetrofitClient
import com.jryingyang.kotlinexercise.repository.TrafficRepository

class TrafficViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrafficInfoViewModel::class.java)) {
            return TrafficInfoViewModel(
                repository = TrafficRepository(
                    RetrofitClient.trafficService()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}