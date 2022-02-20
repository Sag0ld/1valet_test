package com.example.a1valet.di.modules

import com.example.a1valet.views.device.DeviceViewModel
import com.example.a1valet.views.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel(get()) }
    factory { DeviceViewModel(get()) }
}