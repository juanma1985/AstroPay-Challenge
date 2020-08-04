package com.clean.project.app

import android.app.Application
import com.clean.project.app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Koin initializer
        startKoin {
            androidLogger(Level.DEBUG)
            // declare used Android context
            androidContext(this@App)
            // declare modules
            modules(
                listOf(
                    viewModelModules,
                    repositoryModule,
                    actionModule,
                    netModule,
                    apiModule
                )
            )
        }
    }
}