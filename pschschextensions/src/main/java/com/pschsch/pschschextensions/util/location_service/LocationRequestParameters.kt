package com.pschsch.pschschextensions.util.location_service

data class LocationRequestParameters(
    val priority : Int,
    val fastestInterval : Long,
    val interval : Long
)