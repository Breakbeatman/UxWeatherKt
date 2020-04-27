package com.example.uxweatherkt.model

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class RemoteRequestMaker(val listener: Listener) {

    interface Listener {
        fun onRequestReady()
    }

    private lateinit var url: URL
    private lateinit var message: String
    private lateinit var builder: HttpUrl.Builder

    var jsonObject: JSONObject? = null

    fun makeRequest() {

        builder = HttpUrl.parse("http://192.168.1.100:4567/forecast/bycityname")!!.newBuilder()

        builder.addQueryParameter("city", "Kingston")
            .addQueryParameter("type", "current")

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
                message = response.body()?.string()!!
                println(message)
                jsonObject = JSONObject(message)
//                callback
                listener.onRequestReady()
            }
        })
    }
}