package com.example.a1valet.views.device

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1valet.domains.models.Device
import com.example.a1valet.repositories.DeviceRepository
import kotlinx.coroutines.launch

class DeviceViewModel(private val devicesRepo: DeviceRepository) : ViewModel() {
    private val _devices: MutableLiveData<List<Device>> = MutableLiveData()

    val device: LiveData<List<Device>>
        get() = _devices

    init {
        viewModelScope.launch {
            fetchDevices()
        }
    }

    suspend fun fetchDevices() {
        val devices = devicesRepo.getDevices()
        devices.value?.let {
            Log.d("DPII", "message: ${it.message ?: ""}")
            Log.d("DPII", it.exception?.message.toString())
            _devices.postValue(it.data ?: emptyList())
        }
    }
}