package com.clean.project.app.domain.repositories

import com.clean.project.app.domain.models.Weather

interface WeatherRepository {
    suspend fun getWeatherByCityId(cityId: Int): Weather

    suspend fun getWeatherByLatLong(lat: Double, lon: Double): Weather
}