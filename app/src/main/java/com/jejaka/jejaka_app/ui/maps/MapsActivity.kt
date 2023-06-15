package com.jejaka.jejaka_app.ui.maps

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.databinding.ActivityMapsBinding

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        getMyLocation()

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }

    private fun getMyLocation() {

        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true

            // Get the user's last known location
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    // Get the latitude and longitude
                    val latLng = LatLng(location.latitude, location.longitude)
                    val lat = location.latitude
                    val lon = location.longitude

                    // Move the camera to the user's location
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

                    // Add a marker at the user's location
                    mMap.addMarker(
                        MarkerOptions()
                            .position(latLng)
                            .title("My Location")
                    )
                    CoroutineScope(Dispatchers.Main).launch {
                        val address = getAddressFromLocation(lat, lon)

                        val sharedPreferencesLoc = getSharedPreferences("dataLocation", Context.MODE_PRIVATE)
                        val editor = sharedPreferencesLoc.edit()
                        editor.putString("lat", lat.toString())
                        editor.putString("lon", lon.toString())
                        editor.putString("city", address)
                        editor.apply()
                        // Use the address as needed
                        Log.d("Location", "City: $address")
                    }
                }
            }
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private suspend fun getAddressFromLocation(lat: Double, lon: Double): String {
        return withContext(Dispatchers.IO) {
            val geocoder = Geocoder(this@MapsActivity)
//            val addresses = geocoder.getFromLocation(lat, lon, 1)
//            val address = addresses?.get(0)?.getAddressLine(0) // Get the first address line
//            address.toString()
//            val addresses = geocoder.getFromLocation(lat, lon, 1)
//            val cityName = addresses?.getOrNull(0)?.locale
//            cityName.toString()
            val addresses = geocoder.getFromLocation(lat, lon, 1)
            val address = addresses?.getOrNull(0)
            val city = address?.subAdminArea ?: address?.adminArea // Get the city or administrative area
            city.toString()
        }
    }


}