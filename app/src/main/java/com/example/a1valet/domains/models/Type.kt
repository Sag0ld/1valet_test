package com.example.a1valet.domains.models

enum class Type(val value: String) {
    SENSOR("Sensor"),
    THERMOSTAT("Thermostat"),
    UNKNOW("Unknow");

    companion object {
        fun fromName(name: String?): Type {
            return when (name) {
                SENSOR.value -> SENSOR
                THERMOSTAT.value -> THERMOSTAT
                else -> UNKNOW
            }
        }
    }
}