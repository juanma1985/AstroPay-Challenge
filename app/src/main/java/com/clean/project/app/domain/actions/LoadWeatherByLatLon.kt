package com.clean.project.app.domain.actions

import com.clean.project.app.domain.models.Weather
import com.clean.project.app.domain.repositories.WeatherRepository

class LoadWeatherByLatLon(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(lat: Double, lon: Double): Weather {
        return try {
            weatherRepository.getWeatherByLatLong(lat, lon)
        } catch (e: Exception) {
            throw e
        }
    }
}