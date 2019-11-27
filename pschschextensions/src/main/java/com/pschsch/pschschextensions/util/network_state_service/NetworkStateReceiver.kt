package com.pschsch.pschschextensions.util.network_state_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkStateReceiver : BroadcastReceiver(), NetworkStateService.Source {

    override val sourceNetworkState: LiveData<Boolean>
        get() = connectedInternal

    private val connectedInternal = MutableLiveData(true)

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent == null || intent.extras == null) return

        val manager = context.getSystemService<ConnectivityManager>()
        val ni = manager?.activeNetworkInfo
        if (ni == null) {
            connectedInternal.value = false
            return
        }
        connectedInternal.value = ni.isConnected
    }
}