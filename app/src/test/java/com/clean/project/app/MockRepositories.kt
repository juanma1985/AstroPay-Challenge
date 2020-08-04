package com.clean.project.app

import com.clean.project.app.domain.models.Weather
import com.clean.project.app.domain.repositories.WeatherRepository

class InMemoryWeatherRepository(private val weather: Weather) : WeatherRepository {
    override suspend fun getWeatherByCityId(cityId: Int): Weather {
        return weather
    }

    override suspend fun getWeatherByLatLong(lat: Double, lon: Double): Weather {
        return weather
    }
}