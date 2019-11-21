package com.pschsch.pschschextensions.util.network_state_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pschsch.pschschextensions.android_ktx.systemService

class NetworkStateReceiver : BroadcastReceiver(), NetworkStateService.Source {

    override val sourceNetworkState: LiveData<Boolean>
        get() = connectedInternal

    private val connectedInternal = MutableLiveData(true)

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent == null || intent.extras == null) return

        val manager = context.systemService<ConnectivityManager>()
        val ni = manager.activeNetworkInfo
        if (ni == null) {
            connectedInternal.value = false
            return
        }
        connectedInternal.value = ni.isConnected
    }
}