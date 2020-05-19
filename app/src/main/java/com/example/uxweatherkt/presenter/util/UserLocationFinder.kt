package com.example.uxweatherkt.presenter.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.uxweatherkt.MY_PERMISSIONS_FINE_LOCATION
import com.example.uxweatherkt.ui.userLocation.UserLocation

class UserLocationFinder(
    private val context: Context,
    private val userLocation: UserLocation
) {

    interface Listener {
        fun onLocationReady(location: Location)
        fun doNotHavePermission()
    }

    private lateinit var locationManager: LocationManager
    private var listener: Listener? = null

    fun findLocation(locationManager: LocationManager, listener: Listener) {
        this.listener = listener
        this.locationManager = locationManager
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            listener.doNotHavePermission()
        } else {
//            Permission has already been granted
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//                RequestLocationUpdates in Network
                Log.d("NETWORK", "in NETWORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    10,
                    100f,
                    locationListener
                )
//                RequestLocationUpdates in Network
            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Log.d("GPS", "in GPS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    10,
                    100f,
                    locationListener
                )
            }
        }
    }

    private val locationListener: LocationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            onLocationReady(location)
        }

        override fun onProviderDisabled(provider: String) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onStatusChanged(
            provider: String,
            status: Int,
            extras: Bundle
        ) {

        }
    }

    private fun onLocationReady(location: Location) {
        Log.d(
            "LocationFinder",
            location.latitude.toString() + "_______*_______" + location.longitude.toString()
        )
        listener!!.onLocationReady(location)
//        delete MainActivity reference
        listener = null
        locationManager.removeUpdates(locationListener)
        userLocation.location = location
    }
}