package com.pschsch.pschschextensions.util.network_state_service

import androidx.lifecycle.LiveData

interface NetworkStateService {
    val connectionStateLiveData : LiveData<Boolean>
    fun addSource(source: Source)
    fun removeSource(source: Source)

    interface Source {
        val sourceNetworkState : LiveData<Boolean>
    }
}