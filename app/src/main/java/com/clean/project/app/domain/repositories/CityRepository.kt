package com.clean.project.app.domain.repositories

import com.clean.project.app.domain.models.City

interface CityRepository {
    fun getCities(lang: String?): List<City>
}