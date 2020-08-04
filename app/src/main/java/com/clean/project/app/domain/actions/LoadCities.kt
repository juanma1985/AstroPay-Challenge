package com.clean.project.app.domain.actions

import com.clean.project.app.domain.models.City
import com.clean.project.app.domain.repositories.CityRepository

class LoadCities(private val cityRepository: CityRepository) {
    operator fun invoke(lang: String? = null): List<City> {
        return cityRepository.getCities(lang)
    }
}