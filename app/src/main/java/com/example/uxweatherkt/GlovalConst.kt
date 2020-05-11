package com.example.uxweatherkt

const val MY_PERMISSIONS_FINE_LOCATION = 1

const val PROXY_PATH = "http://192.168.1.100:4567/forecast/bylocation"

const val REQUEST_PARAM_BY_LOCATION = "bylocation"
const val REQUEST_PARAM_BY_CITY_NAME = "bycityname"

const val LATITUDE_KEY: String = "latitude"
const val LONGITUDE_KEY: String = "longitude"
const val CITY_NAME_KEY: String = "city"
const val REQUEST_TYPE_KEY: String = "type"

const val REQUEST_TYPE_VALUE_CURRENT: String = "current"
const val REQUEST_TYPE_VALUE_HOURLY: String = "hourly"
const val REQUEST_TYPE_VALUE_DAILY: String = "daily"

const val PARSER_KEY_LIST: String = "list"
const val PARSER_KEY_WEATHER: String = "weather"
const val PARSER_KEY_CODE: String = "code"
const val PARSER_KEY_DESCRIPTION: String = "description"
const val PARSER_KEY_POD: String = "pod"
const val PARSER_KEY_DATE: String = "date"
const val PARSER_KEY_TEMP: String = "temp"
const val PARSER_KEY_MAX_TEMP: String = "max_temp"
const val PARSER_KEY_MIN_TEMP: String = "min_temp"
const val PARSER_KEY_FEEL_LIKE_MAX_TEMP: String = "feel_like_max_temp"
const val PARSER_KEY_FEEL_LIKE_MIX_TEMP: String = "feel_like_app_max_temp"
const val PARSER_KEY_EVE: String = "eve"
const val PARSER_KEY_FEEL_LIKE: String = "feel_like"
const val PARSER_KEY_PRESSURE: String = "pressure"
const val PARSER_KEY_HUMIDITY: String = "humidity"
const val PARSER_KEY_WIND_SPEED: String = "wind_speed"