package com.example.a1valet.datasources.mappers

import com.example.a1valet.domains.models.Device
import com.example.a1valet.domains.models.ID
import com.example.a1valet.domains.models.Price
import com.example.a1valet.domains.models.Type
import com.example.a1valet.datasources.dtos.Device as DeviceDto

class DeviceMapper {
    fun toDevice(device: DeviceDto): Device {
        return Device(
            id = ID(device.id),
            type = Type.fromName(device.type),
            price = Price(device.price, device.Currency),
            isFavorite = device.isFavorite,
            title = device.title,
            description = device.description
        )
    }
}