package com.example.a1valet.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1valet.domains.models.Device
import com.example.a1valet.repositories.DeviceRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val devicesRepo: DeviceRepository) : ViewModel() {
    private val _devices: MutableLiveData<List<Device>> = MutableLiveData()

    val device: LiveData<List<Device>>
        get() = _devices

    init {
        viewModelScope.launch {
//            fetchDevices()
        }
    }

    private suspend fun fetchDevices() {
//        _devices.postValue(devicesRepo.getDevices())
    }
}