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
import java.util.*

class HomeViewModel(
    private val loadCitiesAction: LoadCities
) : ViewModel() {
    private val _citiesData = MutableLiveData<List<City>>()
    private val _openDetails = MutableLiveData<EventData<Int>>()

    val citiesData: LiveData<List<City>> = _citiesData
    val openDetails: LiveData<EventData<Int>> = _openDetails

    private var city: City? = null

    init {
        loadCities()
    }

    private fun loadCities() {
        viewModelScope.launch {
            try {
                _citiesData.value = loadCitiesAction(Locale.getDefault().language)
            } catch (e: Exception) {
                Log.v("ERROR", e.toString())
            }
        }
    }

    fun cityClicked() {
        city?.run {
            _openDetails.value = EventData(id)
        }
    }

    fun setCity(city: City) {
        this.city = city
    }

}