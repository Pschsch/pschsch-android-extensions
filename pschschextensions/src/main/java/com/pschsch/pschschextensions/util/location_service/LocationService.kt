package com.pschsch.pschschextensions.util.location_service

import android.content.Context
import androidx.lifecycle.LiveData
import com.pschsch.pschschextensions.util.location_service.internal.LocationServiceImpl

interface LocationService {

    val locationLiveData : LiveData<LocationData>

    fun startLocationUpdates()
    fun stopLocationUpdates()
    fun close()

    companion object {
        fun create(
            c: Context,
            params: LocationRequestParameters? = null,
            serviceCallback: ((LocationServiceError) -> Unit)? = null
        ): LocationService {
            return LocationServiceImpl(c).apply {
                params?.let { this.locationRequestParams = it }
                this.serviceCallback = serviceCallback
            }
        }
    }
}