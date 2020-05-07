package com.example.uxweatherkt.weather.repository

import com.example.uxweatherkt.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class RemoteRequestMaker {

    private lateinit var url: URL
    private lateinit var builder: HttpUrl.Builder

    fun makeRequest(requestTypeValue: String):JSONObject {

        builder = HttpUrl.parse(PROXY_PATH)!!.newBuilder()

        builder.addQueryParameter(CITY_NAME_KEY, "Murino")
            .addQueryParameter(REQUEST_TYPE_KEY, requestTypeValue)

        url = builder.build().url()
        println(url)
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        var response: Response? = null
        try {
            response = okHttpClient.newCall(request).execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return JSONObject(response!!.body()!!.string())
    }
}