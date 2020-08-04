package com.clean.project.app.infrastructure

import android.content.Context
import com.clean.project.app.domain.models.Location
import com.clean.project.app.domain.repositories.LocationRepository
import com.clean.project.app.ui.commons.extensions.await
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.suspendCoroutine

class AndroidLocationRepository(context: Context) : LocationRepository {
    private val locationProvider: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    override suspend fun getLocation(): Location {
        return try {
            val lastLocation = locationProvider.lastLocation.await()
            Location(lastLocation.latitude, lastLocation.longitude)
        } catch (e: SecurityException) {
            throw e
        }
    }
}