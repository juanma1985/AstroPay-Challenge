package com.clean.project.app.infrastructure

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("/data/2.5/weather?units=metric")
    suspend fun getWeatherByCityId(
        @Query("id") cityId: Int,
        @Query("lang") lang: String? = null
    ): WeatherResponse

    @GET("/data/2.5/weather?units=metric")
    suspend fun getWeatherByLatLong(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String? = null
    ): WeatherResponse
}

class WeatherResponse(
    val weather: List<WeatherEntity>,
    val main: MainEntity,
    val name: String,
    val id: Int,
    val sys: SysEntity,
    val wind: WindEntity
)

data class WeatherEntity(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MainEntity(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val temp_min: Double,
    val temp_max: Double
)

data class SysEntity(
    val sunrise: Long,
    val sunset: Long
)

data class WindEntity(
    val speed: Double
)