package com.example.a1valet.domains.models

data class Device(
    val id: ID,
    val type: Type,
    val price: Price,
    val isFavorite: Boolean,
    val title: String?,
    val description: String?
)

