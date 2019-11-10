package com.pschsch.pschschextensions.util.location_service.internal

import androidx.annotation.IntDef
import com.pschsch.pschschextensions.util.location_service.LocationData

@IntDef(value = [LocationData.TYPE_LAST_KNOWN, LocationData.TYPE_CURRENT, LocationData.TYPE_NULL])
internal annotation class LocationType
