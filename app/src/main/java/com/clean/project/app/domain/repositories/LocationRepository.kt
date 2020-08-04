package com.clean.project.app.domain.repositories

import com.clean.project.app.domain.models.Location

interface LocationRepository {
    suspend fun getLocation(): Location
}