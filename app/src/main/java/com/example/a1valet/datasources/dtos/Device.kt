package com.example.a1valet.datasources.dtos

data class Device(
    val id: String,
    val type: String,
    val price: Double,
    val Currency: String,
    val isFavorite: Boolean,
    val imageUrl: String,
    val title: String,
    val description: String
)