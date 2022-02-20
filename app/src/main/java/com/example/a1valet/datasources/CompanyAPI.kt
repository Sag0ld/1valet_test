package com.example.a1valet.datasources

import com.example.a1valet.datasources.dtos.Device
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CompanyAPI {

    @GET("devices")
    suspend fun getDevices(): Response<List<Device>>
}