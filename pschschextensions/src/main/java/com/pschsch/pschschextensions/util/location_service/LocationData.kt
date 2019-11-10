package com.pschsch.pschschextensions.util.location_service

import android.location.Location
import com.pschsch.pschschextensions.util.location_service.internal.LocationType

data class LocationData(
    val location : Location,
    @LocationType val locationType : Int
) {
    companion object {
        const val TYPE_LAST_KNOWN = -1
        const val TYPE_CURRENT = -2
        const val TYPE_NULL = -3
    }
}