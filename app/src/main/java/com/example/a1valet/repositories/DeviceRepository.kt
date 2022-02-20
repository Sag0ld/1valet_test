package com.example.a1valet.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a1valet.datasources.CompanyAPI
import com.example.a1valet.datasources.mappers.DeviceMapper
import com.example.a1valet.domains.models.Device
import retrofit2.HttpException
import java.net.SocketTimeoutException

class DeviceRepository(private val api: CompanyAPI, private val deviceMapper: DeviceMapper) {
    suspend fun getDevices(): LiveData<Resource<List<Device>>> {
        try {
            val response = api.getDevices()
            if (response.isSuccessful) {
                val devices: List<Device> = response.body()?.map { device ->
                    deviceMapper.toDevice(device = device)
                } ?: emptyList()
                return MutableLiveData(ResponseHandler().handleSuccess(devices))
            }
        } catch (e: Exception) {
            return MutableLiveData(ResponseHandler().handleException(e, emptyList<Device>()))
        }
        return MutableLiveData(null)
    }
}

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception, data: T): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), data)
            is SocketTimeoutException -> Resource.error("Timeout", data)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), data)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val exception: Exception? = null
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS, ERROR, LOADING
}