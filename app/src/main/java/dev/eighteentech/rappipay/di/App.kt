package dev.eighteentech.rappipay.di

import android.app.Application
import dev.eighteentech.rappipay.di.apiModule
import dev.eighteentech.rappipay.di.detailModule
import dev.eighteentech.rappipay.di.mainModule
import dev.eighteentech.rappipay.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App:Application() {
    private val modules = listOf(apiModule, mainModule, detailModule, searchModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            koin.loadModules(modules)
        }

    }
}