package com.example.uxweatherkt.Model

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URL

class WeatherRequestMaker {

    fun doRequest (url: URL): JSONObject{

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val call = okHttpClient.newCall(request)
        val response = call.execute()
        val message = response.body()?.string()
        return JSONObject(message)
    }

}