package com.clean.project.app

import com.clean.project.app.domain.actions.LoadWeatherByCityId
import com.clean.project.app.domain.models.*
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.junit.Test

class LoadWeatherByCityIdTest {
    @Test
    fun `should return weather for selected city id`() = runBlockingTest {
        val loadWeatherByCityId = LoadWeatherByCityId(InMemoryWeatherRepository(aWeather()))

        val expected = aWeather()

        Assertions.assertThat(loadWeatherByCityId(1)).isEqualTo(expected)
    }

    private fun aWeather(): Weather {
        return Weather(
            info = Info("description", "icon", 1.2),
            main = Main(10, 80, 10, 10, 10),
            hours = Hours("07:10", "20:00"),
            city = City(-1, "London")
        )
    }
}