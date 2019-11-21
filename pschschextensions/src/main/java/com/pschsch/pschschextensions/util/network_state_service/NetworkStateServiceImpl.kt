package com.pschsch.pschschextensions.util.network_state_service

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.pschsch.pschschextensions.ktx.orTrue
import com.pschsch.pschschextensions.util.helpers_extensions.SingleLiveEvent
import java.util.concurrent.Executors

class NetworkStateServiceImpl : NetworkStateService {

    constructor()
    constructor(c: Context) {
        val broadCastSource = NetworkStateReceiver()
        addSource(broadCastSource)
        c.registerReceiver(broadCastSource, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    private val executor = Executors.newSingleThreadExecutor()
    private val handler = android.os.Handler(Looper.getMainLooper())

    private val sources = HashMap<NetworkStateService.Source, Observer<Boolean>>()

    private val connectionStateLiveDataInternal = SingleLiveEvent<Boolean>()

    override val connectionStateLiveData: LiveData<Boolean>
        get() = Transformations.distinctUntilChanged(connectionStateLiveDataInternal)

    override fun addSource(source: NetworkStateService.Source) {
        executor.execute {
            if (!sources.contains(source)) {
                val observer = Observer<Boolean> {
                    handleNetworkState()
                }
                handler.post { source.sourceNetworkState.observeForever(observer) }
                sources[source] = observer
            }
        }
    }

    override fun removeSource(source: NetworkStateService.Source) {
        executor.execute {
            sources.remove(source)
        }
    }

    private fun handleNetworkState() {
        executor.execute {
            sources.forEach {
                val currentSourceValue = it.key.sourceNetworkState.value.orTrue()
                if (!currentSourceValue) {
                    connectionStateLiveDataInternal.postValue(false)
                    return@execute
                }
            }
            connectionStateLiveDataInternal.postValue(true)
        }
    }
}