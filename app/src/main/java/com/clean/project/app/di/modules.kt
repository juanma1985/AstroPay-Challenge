package com.clean.project.app.di

import android.app.Application
import com.clean.project.app.domain.actions.GetCurrentLocation
import com.clean.project.app.domain.actions.LoadCities
import com.clean.project.app.domain.actions.LoadWeatherByCityId
import com.clean.project.app.domain.actions.LoadWeatherByLatLon
import com.clean.project.app.domain.repositories.CityRepository
import com.clean.project.app.domain.repositories.LocationRepository
import com.clean.project.app.domain.repositories.WeatherRepository
import com.clean.project.app.infrastructure.AndroidLocationRepository
import com.clean.project.app.infrastructure.ApiClient
import com.clean.project.app.infrastructure.InMemoryCityRepository
import com.clean.project.app.infrastructure.WeatherNetwork
import com.clean.project.app.ui.details.DetailsViewModel
import com.clean.project.app.ui.home.HomeViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModules = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailsViewModel(get(), get(), get()) }
}

val repositoryModule = module {
    single<WeatherRepository> { WeatherNetwork(get()) }
    single<CityRepository> { InMemoryCityRepository() }
    single<LocationRepository> { AndroidLocationRepository(androidContext()) }
}

val actionModule = module {
    single { LoadCities(get()) }
    single { LoadWeatherByCityId(get()) }
    single { LoadWeatherByLatLon(get()) }
    single { GetCurrentLocation(get()) }
}

val apiModule = module {
    fun provideWeatherApi(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    single { provideWeatherApi(get()) }
}

val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideRequestInterceptor(apiKey: String): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()

                val url = request.url.newBuilder()
                    .addQueryParameter("appid", apiKey)
                    .build()

                val newRequest = request.newBuilder()
                    .url(url)
                    .build()

                return chain.proceed(newRequest)
            }
        }
    }

    fun provideHttpClient(cache: Cache, interceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(logging)
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get(), provideRequestInterceptor("36ba0cf0ffea0763f43081c134c0336c")) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }

}