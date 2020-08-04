package com.clean.project.app

import com.clean.project.app.domain.actions.LoadCities
import com.clean.project.app.domain.models.City
import com.clean.project.app.infrastructure.InMemoryCityRepository
import org.assertj.core.api.Assertions
import org.junit.Test

class LoadCitiesTest {

    @Test
    fun `should return list of cities with default language when lang parameter is null`() {
        val loadCities = LoadCities(InMemoryCityRepository())

        val expected = aListOfCitesInDefaultLanguage()

        Assertions.assertThat(loadCities()).isEqualTo(expected)
    }

    @Test
    fun `should return list of cities with default language when lang parameter is not recognized`() {
        val loadCities = LoadCities(InMemoryCityRepository())

        val expected = aListOfCitesInDefaultLanguage()

        Assertions.assertThat(loadCities(UNKNOWN_LANGUAGE)).isEqualTo(expected)
    }

    @Test
    fun `should return list of cities with spanish language when lang parameter is ES`() {
        val loadCities = LoadCities(InMemoryCityRepository())

        val expected = aListOfCitesInSpanishLanguage()

        Assertions.assertThat(loadCities(SPANISH)).isEqualTo(expected)
    }

    private fun aListOfCitesInDefaultLanguage(): List<City> {
        return listOf(
            City(-1, "Current Location"),
            City(3441575, "Montevideo"),
            City(3846616, "Londres"),
            City(3448439, "São Paulo"),
            City(3435910, "Buenos Aires"),
            City(2867714, "Munich")
        )
    }

    private fun aListOfCitesInSpanishLanguage(): List<City> {
        return listOf(
            City(-1, "Ubicación actual"),
            City(3441575, "Montevideo"),
            City(3846616, "Londres"),
            City(3448439, "São Paulo"),
            City(3435910, "Buenos Aires"),
            City(2867714, "Munich")
        )
    }

    companion object {
        private const val UNKNOWN_LANGUAGE = "ar"
        private const val SPANISH = "es"
    }

}