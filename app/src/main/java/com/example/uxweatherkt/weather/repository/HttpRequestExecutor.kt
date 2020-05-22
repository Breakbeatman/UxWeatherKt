package com.example.uxweatherkt.weather.repository

import com.example.uxweatherkt.*
import com.example.uxweatherkt.presenter.util.Coordinates
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class HttpRequestExecutor {

    fun makeRequest(requestTypeValue: String, coordinates: Coordinates): JSONObject? {
        val url = getUrlByCoors(requestTypeValue, coordinates)
        return execute(url)
    }

    fun makeRequest(requestTypeValue: String, cityName: String): JSONObject? {
        val url = getUrlByCityName(requestTypeValue, cityName)
        return execute(url)
    }

    private fun getUrlByCoors(requestTypeValue: String, coordinates: Coordinates): URL {
        val builder = HttpUrl.parse(PROXY_SERVER_PATH + REQUEST_PARAM_BY_LOCATION)!!.newBuilder()

        builder.addQueryParameter(LATITUDE_KEY, coordinates.latitude)
            .addQueryParameter(LONGITUDE_KEY, coordinates.longitude)
            .addQueryParameter(REQUEST_TYPE_KEY, requestTypeValue)

        return builder.build().url()
    }

    private fun getUrlByCityName(requestTypeValue: String, cityName: String): URL {
        val builder = HttpUrl.parse(PROXY_SERVER_PATH + REQUEST_PARAM_BY_CITY_NAME)!!.newBuilder()

        builder.addQueryParameter(CITY_NAME_KEY, cityName)
            .addQueryParameter(REQUEST_TYPE_KEY, requestTypeValue)

        return builder.build().url()
    }

    private fun execute(url: URL): JSONObject? {
        val response: JSONObject?
        try {
             response = executeCall(url)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return response
    }

    private fun executeCall(url: URL): JSONObject? {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            }
            return JSONObject(response.body()!!.string())
        }
    }
}