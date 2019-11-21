package com.pschsch.pschschextensions.util.location_service.internal

import android.content.Context
import android.location.Location
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import com.pschsch.pschschextensions.util.location_service.*
import com.pschsch.pschschextensions.util.location_service.LocationData.Companion.TYPE_NULL
import java.lang.Exception

class LocationServiceImpl internal constructor(context: Context) :
    LocationService {

    override val locationLiveData: LiveData<LocationData>
        get() = locationLiveDataInternal

    private val hThread = HandlerThread(LOCATION_HANDLER_THREAD_NAME)
    private val handler = Handler(Looper.getMainLooper())
    private val locationLiveDataInternal = MutableLiveData<LocationData>(LocationData(Location(""), TYPE_NULL))
    private val locationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(
            context
        )
    }
    private val locationRequest: LocationRequest by lazy { LocationRequest() }
    private var locationCallback: LocationCallback? = null
    private var isLocationReceiving: Boolean = false

    internal var locationRequestParams =
        LocationRequestParameters(
            LOCATION_PRIORITY_DEFAULT,
            LOCATION_FASTEST_INTERVAL_DEFAULT.toLong(),
            LOCATION_INTERVAL_DEFAULT.toLong()
        )

    internal var serviceCallback: ((LocationServiceError) -> Unit)? = null

    init {
        hThread.start()
    }

    override fun startLocationUpdates() {
        if (isLocationReceiving) return
        isLocationReceiving = true
        try {
            locationClient.lastLocation.addOnSuccessListener {
                it?.let { loc ->
                    locationLiveDataInternal.postValue(
                        LocationData(
                            loc,
                            LocationData.TYPE_LAST_KNOWN
                        )
                    )
                }
            }
            locationRequest.fastestInterval = locationRequestParams.fastestInterval
            locationRequest.interval = locationRequestParams.interval
            locationRequest.priority = locationRequestParams.priority


            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    val location = locationResult?.lastLocation
                    location?.let { loc ->
                        locationLiveDataInternal.postValue(
                            LocationData(
                                loc,
                                LocationData.TYPE_CURRENT
                            )
                        )
                    }
                }
            }


        } catch (e: SecurityException) {
            handler.post { serviceCallback?.invoke(LocationServiceError.PermissionError) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        locationClient.requestLocationUpdates(locationRequest, locationCallback, hThread.looper)
    }

    override fun stopLocationUpdates() {
        locationCallback?.let {
            locationClient.removeLocationUpdates(it)
        }
        isLocationReceiving = false
    }

    override fun close(){
        hThread.quit()
    }

    companion object {
        private const val LOCATION_HANDLER_THREAD_NAME = "LocationServiceHandlerThread"
        private const val LOCATION_PRIORITY_DEFAULT =
            LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        private const val LOCATION_FASTEST_INTERVAL_DEFAULT = 2000
        private const val LOCATION_INTERVAL_DEFAULT = 2000
    }

}
