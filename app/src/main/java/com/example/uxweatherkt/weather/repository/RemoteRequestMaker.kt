package com.example.uxweatherkt.weather.repository

import android.util.Log
import com.example.uxweatherkt.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class RemoteRequestMaker {

    fun makeRequest(requestTypeValue: String, latitude: String, longitude: String):JSONObject? {
        Log.d("TAG", "THREAD NOW" + Thread.currentThread().name)
        val builder = HttpUrl.parse(PROXY_PATH)!!.newBuilder()
        Log.d("TAG", "THREAD NOW" + Thread.currentThread().name)
//        builder.addQueryParameter(CITY_NAME_KEY, "Murino")
//            .addQueryParameter(REQUEST_TYPE_KEY, requestTypeValue)

        builder.addQueryParameter(LATITUDE_KEY, latitude)
            .addQueryParameter(LONGITUDE_KEY, longitude)
            .addQueryParameter(REQUEST_TYPE_KEY, requestTypeValue)
        Log.d("TAG", "THREAD NOW" + Thread.currentThread().name)

        val url: URL = builder.build().url()
        println(url)
        Log.d("TAG", "THREAD NOW" + Thread.currentThread().name)
        val okHttpClient = OkHttpClient()
        Log.d("TAG", "THREAD NOW" + Thread.currentThread().name)
        val request = Request.Builder()
            .url(url)
            .build()
        Log.d("TAG", "THREAD NOW" + Thread.currentThread().name)
        var response: Response? = null
        Log.d("TAG", "THREAD NOW" + Thread.currentThread().name)
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