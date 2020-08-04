package com.clean.project.app

import com.clean.project.app.domain.actions.GetCurrentLocation
import com.clean.project.app.domain.actions.LoadCities
import com.clean.project.app.domain.actions.NotPermissionsException
import com.clean.project.app.domain.models.City
import com.clean.project.app.domain.models.Location
import com.clean.project.app.domain.repositories.CityRepository
import com.clean.project.app.domain.repositories.LocationRepository
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.junit.Test

class GetCurrentLocationTest {

    @Test
    fun `should return current location if success`() = runBlockingTest {
        val getCurrentLocation = GetCurrentLocation(InMemoryLocationRepository(aLocation()))

        val expected = aLocation()

        Assertions.assertThat(getCurrentLocation()).isEqualTo(expected)
    }

    @Test
    fun `should return permission exception if fail`() = runBlockingTest {
        try {
            GetCurrentLocation(InMemoryLocationRepository(null))
        } catch (e: NotPermissionsException) {
            Assertions.assertThat(e).isEqualTo(NotPermissionsException())
        }
    }

    private fun aLocation(): Location {
        return Location(1.1, 2.2)
    }

}

class InMemoryLocationRepository(private val location: Location?) : LocationRepository {
    override suspend fun getLocation(): Location {
        return location ?: throw NotPermissionsException()
    }

}
