package com.pschsch.pschschextensions.util.network_state_service

import android.content.Context

object NetworkStateServiceFactory {
    fun emptyService() : NetworkStateService {
        return NetworkStateServiceImpl()
    }

    fun broadCastService(c : Context) : NetworkStateService {
        return NetworkStateServiceImpl(c)
    }
}