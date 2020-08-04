package com.clean.project.app.domain.models

data class Weather(
    val info: Info,
    val main: Main,
    val hours: Hours,
    val city: City
)

data class Main(
    val pressure: Int,
    val humidity: Int,
    val temp: Int,
    val tempMin: Int,
    val tempMax: Int
)

data class Info(
    val description: String,
    val icon: String,
    val windSpeed: Double
)

data class Hours(
    val sunrise: String,
    val sunset: String
)