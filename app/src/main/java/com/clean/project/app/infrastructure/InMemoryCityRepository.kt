package com.clean.project.app.infrastructure

import com.clean.project.app.domain.models.City
import com.clean.project.app.domain.repositories.CityRepository

class InMemoryCityRepository: CityRepository {
    override fun getCities(): List<City> {
        return listOf(
            City(-1, "Current Location"),
            City(3441575, "Montevideo"),
            City(3846616, "Londres"),
            City(3448439, "San Pablo"),
            City(3435910, "Buenos Aires"),
            City(2867714, "Munich")
        )
    }
}