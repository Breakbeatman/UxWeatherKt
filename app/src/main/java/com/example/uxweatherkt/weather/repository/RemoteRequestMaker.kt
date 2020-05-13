package com.example.uxweatherkt.weather.repository

import com.example.uxweatherkt.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class RemoteRequestMaker {

    fun makeRequest(requestTypeValue: String, latitude: String, longitude: String): JSONObject? {
        val builder = HttpUrl.parse(PROXY_PATH + REQUEST_PARAM_BY_LOCATION)!!.newBuilder()

        builder.addQueryParameter(LATITUDE_KEY, latitude)
            .addQueryParameter(LONGITUDE_KEY, longitude)
            .addQueryParameter(REQUEST_TYPE_KEY, requestTypeValue)

        val url: URL = builder.build().url()
        println(url)
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response: Response?
        try {
            response = okHttpClient.newCall(request).execute()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        if (response == null) {
            return null
        }
        return JSONObject(response.body()!!.string())
    }

    fun makeRequest(requestTypeValue: String, cityName: String): JSONObject? {
        val builder = HttpUrl.parse(PROXY_PATH + REQUEST_PARAM_BY_CITY_NAME)!!.newBuilder()

        builder.addQueryParameter(CITY_NAME_KEY, cityName)
            .addQueryParameter(REQUEST_TYPE_KEY, requestTypeValue)

        val url: URL = builder.build().url()
        println(url)
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response: Response?
        try {
            response = okHttpClient.newCall(request).execute()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        if (response == null) {
            return null
        }
        return JSONObject(response.body()!!.string())
    }
}