package com.pschsch.pschschextensions.util.location_service.internal

import androidx.annotation.IntDef
import com.pschsch.pschschextensions.util.location_service.LocationData

@IntDef(value = [LocationData.TYPE_LAST_KNOWN, LocationData.TYPE_CURRENT])
internal annotation class LocationType
