package com.example.a1valet.datasources.mocks

import com.example.a1valet.datasources.dtos.Device

val devices: List<Device> = listOf(
    Device(
        "1234", "sensor", 20.0,
        "USD", false, "",
        "Test Sensor", "No description"
    ),
    Device(
        "1235", "Thermostat", 25.0,
        "USD", false, "",
        "Test Thermostat", "No description"
    )
)