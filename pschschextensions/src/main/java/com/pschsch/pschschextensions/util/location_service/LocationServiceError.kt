package com.pschsch.pschschextensions.util.location_service

sealed class LocationServiceError {
    object PermissionError : LocationServiceError()
}