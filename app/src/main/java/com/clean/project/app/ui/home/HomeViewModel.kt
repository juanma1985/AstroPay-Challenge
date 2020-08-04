package com.clean.project.app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.project.app.domain.actions.GetCurrentLocation
import com.clean.project.app.domain.actions.LoadCities
import com.clean.project.app.domain.models.City
import com.clean.project.app.ui.commons.EventData
import kotlinx.coroutines.launch

class HomeViewModel(
    private val loadCitiesAction: LoadCities,
    private val getCurrentLocation: GetCurrentLocation
) : ViewModel() {
    private val _citiesData = MutableLiveData<List<City>>()
    private val _openDetails = MutableLiveData<EventData<Int>>()

    val citiesData: LiveData<List<City>> = _citiesData
    val openDetails: LiveData<EventData<Int>> = _openDetails

    init {
        loadCities()
    }

    private fun loadCities() {
        viewModelScope.launch {
            try {
                _citiesData.value = loadCitiesAction()
            } catch (e: Exception) {
                Log.v("ERROR", e.toString())
            }
        }
    }

    fun cityClicked(city: City) {
        _openDetails.value = EventData(city.id)
    }

}