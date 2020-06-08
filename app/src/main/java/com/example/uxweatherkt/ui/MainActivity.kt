package com.example.uxweatherkt.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.example.uxweatherkt.*

import com.example.uxweatherkt.presenter.util.UserLocationFinder
import com.example.uxweatherkt.ui.userLocation.UserLocation


class MainActivity : AppCompatActivity(), UserLocationFinder.Listener {

    private lateinit var locationManager: LocationManager
    private lateinit var userLocation: UserLocation
    private lateinit var userLocationFinder: UserLocationFinder

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (checkPermission()) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(
                    this,
                    "Permission need for find your location and provide accurate forecast",
                    Toast.LENGTH_SHORT
                ).show()
            }
            getUserPermission()
        }
        init()
        initMainFragment(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (!checkPermission()) {
            findUserLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {
            MY_PERMISSIONS_FINE_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    findUserLocation()
                } else {
                    startCityNameDialog()
                    Toast.makeText(this, "Permission was not granted", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    override fun onLocationReady(location: Location) {
        val mainFragment =
            supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG) as MainFragment
        mainFragment.passLocation(location)
    }

    override fun doNotHavePermission() {
        getUserPermission()
    }

    private fun init() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        userLocation = (application as App).getDependencyRoot().userLocation
        userLocationFinder = (application as App).getDependencyRoot().locationFinder
    }

    private fun initMainFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_main__fragment_container, MainFragment(), MAIN_FRAGMENT_TAG)
                .commit()
        }
    }

    private fun checkPermission(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                )
    }

    private fun getUserPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), MY_PERMISSIONS_FINE_LOCATION
        )
    }

    private fun findUserLocation() {
        if (userLocation.location == null) {
            userLocationFinder.findLocation(locationManager, this)
        } else {
            onLocationReady(userLocation.location as Location)
        }
    }

    private fun startCityNameDialog() {
        MaterialDialog(this).show {
            input(hintRes = R.string.enter_your_location_name) { dialog, text ->

                val cityName = text.toString()
                cityNameReady(cityName)
                userLocation.city = cityName
                Log.d("TAG", "your city name $cityName")
            }
            positiveButton(R.string.submit)
            negativeButton(R.string.deny)
        }
    }

    private fun cityNameReady(cityName: String) {
        val mainFragment = supportFragmentManager.findFragmentByTag("fr1") as MainFragment
        mainFragment.passCityName(cityName)
    }

}