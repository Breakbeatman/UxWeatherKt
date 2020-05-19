package com.example.uxweatherkt.weather.repository

import com.example.uxweatherkt.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class HttpRequestExecutor {

    fun makeRequest(requestTypeValue: String, latitude: String, longitude: String): JSONObject? {
        val response: JSONObject?
        val builder = HttpUrl.parse(PROXY_SERVER_PATH + REQUEST_PARAM_BY_LOCATION)!!.newBuilder()

        builder.addQueryParameter(LATITUDE_KEY, latitude)
            .addQueryParameter(LONGITUDE_KEY, longitude)
            .addQueryParameter(REQUEST_TYPE_KEY, requestTypeValue)
//        TODO: catch
        try {
            response = callExecute(builder)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return response
    }

    fun makeRequest(requestTypeValue: String, cityName: String): JSONObject? {
        val builder = HttpUrl.parse(PROXY_SERVER_PATH + REQUEST_PARAM_BY_CITY_NAME)!!.newBuilder()

        builder.addQueryParameter(CITY_NAME_KEY, cityName)
            .addQueryParameter(REQUEST_TYPE_KEY, requestTypeValue)

        return callExecute(builder)
    }

    private fun callExecute(builder: HttpUrl.Builder): JSONObject? {
        val url: URL = builder.build().url()
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