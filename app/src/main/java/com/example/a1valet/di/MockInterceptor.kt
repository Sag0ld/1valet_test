package com.example.a1valet.di

import com.example.a1valet.BuildConfig
import okhttp3.*

class MockInterceptor : Interceptor {
    private val getListOfReposBeingStarredJson =
        """
            devices: [
            {
            “Id”: “1234”,
            “Type”: “Sensor”,
            “Price”: 20,
            “Currency”: “USD”, “isFavorite”: false,
            “imageUrl: “”, “Title”: “Test Sensor”, “Description: “”,
            },{
            “Id”: “1235”,
            “Type”: “Thermostat”,
            “Price”: 25, “Currency”: “USD”,
            “isFavorite”: false, “imageUrl: “”,
            “Title”: “Test Thermostat”, “Description: “”,
            },
            ]
        """

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            val responseString = when {
                uri.endsWith("devices") -> getListOfReposBeingStarredJson
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }
}