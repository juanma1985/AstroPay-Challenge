package com.clean.project.app.domain.actions

import com.clean.project.app.domain.models.Weather
import com.clean.project.app.domain.repositories.WeatherRepository

class LoadWeatherByCityId(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(cityId: Int): Weather {
        return try {
            weatherRepository.getWeatherByCityId(cityId)
        } catch (e: Exception) {
            throw e
        }
    }
}