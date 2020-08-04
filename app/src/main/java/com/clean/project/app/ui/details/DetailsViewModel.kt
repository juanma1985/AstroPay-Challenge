package com.clean.project.app.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.project.app.domain.actions.GetCurrentLocation
import com.clean.project.app.domain.actions.LoadWeatherByCityId
import com.clean.project.app.domain.actions.LoadWeatherByLatLon
import com.clean.project.app.domain.models.City
import com.clean.project.app.domain.models.Weather
import com.clean.project.app.ui.commons.EventData
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val loadWeatherByCityId: LoadWeatherByCityId,
    private val loadWeatherByLatLon: LoadWeatherByLatLon,
    private val getCurrentLocation: GetCurrentLocation
) : ViewModel() {

    private val _weatherData = MutableLiveData<Weather>()

    val weatherData: LiveData<Weather> = _weatherData

    fun loadWeather(cityId: Int) {
        if (cityId == CURRENT_LOCATION) {
            getByLocation()
        } else {
            getById(cityId)
        }
    }

    private fun getByLocation() {
        viewModelScope.launch {
            try {
                val currentLocation = getCurrentLocation()
                val result = loadWeatherByLatLon(currentLocation.lat, currentLocation.lon)
                _weatherData.value = result
            } catch (e: Exception) {
                Log.v("ERROR", e.toString())
            }
        }
    }

    private fun getById(cityId: Int) {
        viewModelScope.launch {
            try {
                val result = loadWeatherByCityId(cityId)
                _weatherData.value = result
            } catch (e: Exception) {
                Log.v("ERROR", e.toString())
            }
        }
    }

    companion object {
        private const val CURRENT_LOCATION = -1
    }
}