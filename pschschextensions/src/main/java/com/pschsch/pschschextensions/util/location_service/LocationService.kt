package com.pschsch.pschschextensions.util.location_service

import android.content.Context
import androidx.lifecycle.LiveData
import com.pschsch.pschschextensions.util.location_service.internal.LocationServiceImpl

interface LocationService {

    val locationLiveData : LiveData<LocationData>

    fun setCallback(callback: LocationServiceCallback) : LocationService
    fun setRequestParams(params : LocationRequestParameters) : LocationService
    fun startLocationUpdates()
    fun stopLocationUpdates()
    fun close()

    companion object {
        fun create(c : Context): LocationService {
            return LocationServiceImpl(c)
        }
    }
}