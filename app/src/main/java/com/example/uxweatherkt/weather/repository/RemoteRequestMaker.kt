package com.example.uxweatherkt.weather.repository

import com.example.uxweatherkt.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class RemoteRequestMaker(val listener: Listener) {

    interface Listener {
        fun onRequestReady(requestTypeValue: String, response: JSONObject)
    }

    private lateinit var url: URL
    private lateinit var message: String
    private lateinit var builder: HttpUrl.Builder

    fun makeRequest(requestTypeValue: String) {

        builder = HttpUrl.parse(PROXY_PATH)!!.newBuilder()

        builder.addQueryParameter(CITY_NAME_KEY, "Murino")
            .addQueryParameter(REQUEST_TYPE_KEY, requestTypeValue)

        url = builder.build().url()
        println(url)
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseW = JSONObject(response.body()!!.string())
//                callback
                listener.onRequestReady(requestTypeValue, responseW)
            }
        })
    }
}