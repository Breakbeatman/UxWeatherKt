package com.example.uxweatherkt

const val PROXY_PATH = "http://192.168.1.100:4567/forecast/bycityname"

const val REQUEST_PARAM_BY_LOCATION = "bylocation"
const val REQUEST_PARAM_BY_CITY_NAME = "bycityname"

const val LATITUDE_KEY: String = "latitude"
const val LONGITUDE_KEY: String = "longitude"
const val CITY_NAME_KEY: String = "city"
const val REQUEST_TYPE_KEY: String = "type"

const val REQUEST_TYPE_VALUE_CURRENT: String = "current"
const val REQUEST_TYPE_VALUE_HOURLY: String = "hourly"
const val REQUEST_TYPE_VALUE_DAILY: String = "daily"