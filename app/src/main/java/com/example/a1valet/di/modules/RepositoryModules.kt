package com.example.a1valet.di.modules

import com.example.a1valet.datasources.mappers.DeviceMapper
import com.example.a1valet.repositories.DeviceRepository
import org.koin.dsl.module

val repositoryModules = module {
    factory { DeviceRepository(get(), get()) }
}

val mappersModules = module {
    single { DeviceMapper() }
}
