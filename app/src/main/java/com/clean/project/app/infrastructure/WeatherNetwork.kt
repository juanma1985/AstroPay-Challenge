package com.clean.project.app.infrastructure

import com.clean.project.app.domain.models.*
import com.clean.project.app.domain.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class WeatherNetwork(private val apiClient: ApiClient) :
    WeatherRepository {

    override suspend fun getWeatherByCityId(cityId: Int): Weather {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getWeatherByCityId(cityId, Locale.getDefault().language)
            mapToWeather(response)
        }
    }

    override suspend fun getWeatherByLatLong(lat: Double, lon: Double): Weather {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getWeatherByLatLong(lat, lon, Locale.getDefault().language)
            mapToWeather(response)
        }
    }

    private fun mapToWeather(response: WeatherResponse): Weather {
        val weather = response.weather[0]
        return Weather(
            Info(weather.description.capitalize(), getIconUrl(weather.icon), response.wind.speed),
            Main(
                response.main.pressure,
                response.main.humidity,
                response.main.temp.toInt(),
                response.main.temp_min.toInt(),
                response.main.temp_max.toInt()
            ),
            Hours(getDateTime(response.sys.sunrise), getDateTime(response.sys.sunset)),
            City(response.id, response.name)
        )
    }

    private fun getDateTime(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return simpleDateFormat.format(Date(date * 1000))
    }

    private fun getIconUrl(icon: String): String {
        return "http://openweathermap.org/img/wn/${icon}@2x.png"
    }
}