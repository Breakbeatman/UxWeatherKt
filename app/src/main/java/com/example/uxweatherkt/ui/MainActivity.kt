package com.example.uxweatherkt.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

import com.example.uxweatherkt.App
import com.example.uxweatherkt.MY_PERMISSIONS_FINE_LOCATION
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.util.LocationFinder
import com.example.uxweatherkt.ui.userLocation.UserLocation
import com.example.uxweatherkt.ui.currentWeatherView.CurrentWeatherFragment
import com.example.uxweatherkt.ui.dailyForecastView.DailyForecastListFragment

class MainActivity : AppCompatActivity(), LocationFinder.Listener {

    private lateinit var locationManager: LocationManager
    private lateinit var userLocation: UserLocation
    private lateinit var locationFinder: LocationFinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), MY_PERMISSIONS_FINE_LOCATION
            )
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        } else {
            // Permission has already been granted
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        }
        //  TODO: init
        userLocation = (application as App).getDependencyRoot().userLocation
        if (userLocation.location == null) {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationFinder = (application as App).getDependencyRoot().locationFinder
            locationFinder.findLocation(locationManager, this)
        } else {
            onLocationReady(userLocation.location as Location)
        }
        initFragments(savedInstanceState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }

            }
        }
    }

    private fun initFragments(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.activity_main__fragment_container,
                    CurrentWeatherFragment(), "fr1"
                )
                .commit()
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.activity_main__fragment_container2,
                    DailyForecastListFragment(), "fr2"
                )
                .commit()
        }
    }

    override fun onLocationReady(location: Location) {
        (supportFragmentManager.findFragmentByTag("fr1") as CurrentWeatherFragment).onLocationReady(
            location
        )
        (supportFragmentManager.findFragmentByTag("fr2") as DailyForecastListFragment).onLocationReady(
            location
        )
    }
}
