package com.clean.project.app.infrastructure

import com.clean.project.app.domain.models.City
import com.clean.project.app.domain.repositories.CityRepository

class InMemoryCityRepository : CityRepository {
    override fun getCities(lang: String?): List<City> {
        if (lang == SPANISH) {
            return getCitiesInSpanishLanguage()
        }
        return getCitiesDefaultLanguage()
    }

    private fun getCitiesDefaultLanguage(): List<City> {
        val result = getCities()
        result.add(0, City(-1, "Current Location"))
        return result.toList()
    }

    private fun getCitiesInSpanishLanguage(): List<City> {
        val result = getCities()
        result.add(0, City(-1, "Ubicación actual"))
        return result.toList()
    }

    private fun getCities(): MutableList<City> {
        return mutableListOf(
            City(3441575, "Montevideo"),
            City(3846616, "Londres"),
            City(3448439, "São Paulo"),
            City(3435910, "Buenos Aires"),
            City(2867714, "Munich")
        )
    }

    companion object {
        private const val SPANISH = "es"
    }
}