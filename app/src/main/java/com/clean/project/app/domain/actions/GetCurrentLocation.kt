package com.clean.project.app.domain.actions

import com.clean.project.app.domain.models.Location
import com.clean.project.app.domain.repositories.LocationRepository

class GetCurrentLocation(private val locationRepository: LocationRepository) {
    suspend operator fun invoke(): Location {
        return try {
            locationRepository.getLocation()
        } catch (e: SecurityException){
            throw NotPermissionsException()
        }
    }
}

class NotPermissionsException : Exception()