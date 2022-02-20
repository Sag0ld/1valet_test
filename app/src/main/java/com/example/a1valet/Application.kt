package com.example.a1valet

import android.app.Application
import com.example.a1valet.di.modules.mappersModules
import com.example.a1valet.di.modules.networkModules
import com.example.a1valet.di.modules.repositoryModules
import com.example.a1valet.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(repositoryModules, mappersModules, networkModules, viewModelModule))
        }
    }
}