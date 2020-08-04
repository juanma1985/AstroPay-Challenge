package com.clean.project.app

import com.clean.project.app.domain.actions.LoadCities
import com.clean.project.app.domain.models.City
import com.clean.project.app.domain.repositories.CityRepository
import com.clean.project.app.infrastructure.InMemoryCityRepository
import org.assertj.core.api.Assertions
import org.junit.Test

class LoadCitiesTest {

    @Test
    fun `should return list of cities`() {
        val loadCities = LoadCities(InMemoryCityRepository())

        val expected = aListOfCites()

        Assertions.assertThat(loadCities()).isEqualTo(expected)
    }

    private fun aListOfCites(): List<City> {
        return listOf(
            City(-1, "Current Location"),
            City(3441575, "Montevideo"),
            City(3846616, "Londres"),
            City(3448439, "San Pablo"),
            City(3435910, "Buenos Aires"),
            City(2867714, "Munich")
        )
    }

}