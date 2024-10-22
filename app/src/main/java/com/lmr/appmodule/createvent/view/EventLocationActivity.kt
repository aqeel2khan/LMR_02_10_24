package com.lmr.appmodule.createvent.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.lmr.R
import com.lmr.databinding.ActivityLocationBinding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class EventLocationActivity :  BaseActivity<ActivityLocationBinding>(), OnMapReadyCallback {
    private val viewModel: LocationViewModel by viewModels()

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun getViewModel(): BaseViewModel {
        return  viewModel
    }

    override fun initUi() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        //<-- Header Text View -->
        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        detailsTextView.text = "Location Details"
        binding.addLocationLater.setOnClickListener {
            startActivity(Intent(this, EventOrganizerDetailsActivity::class.java))
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    override fun getViewBinding()=ActivityLocationBinding.inflate(layoutInflater)

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Check for location permission and enable user location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
            getDeviceLocation()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun getAddressFromLocation(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                val addressText = address.getAddressLine(0)
                binding.tvAddress.text = addressText?.toString()
              //  Toast.makeText(this, "Address: $addressText", Toast.LENGTH_LONG).show()
            } else {
              //  Toast.makeText(this, "Unable to fetch address", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
           // Toast.makeText(this, "Unable to fetch address", Toast.LENGTH_LONG).show()
        }
    }

    private fun getDeviceLocation() {
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                val locationResult = fusedLocationClient.lastLocation
                locationResult.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val lastKnownLocation: Location? = task.result
                        if (lastKnownLocation != null) {
                            val currentLatLng = LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
                            mMap.addMarker(MarkerOptions().position(currentLatLng).title("You are here"))
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                            getAddressFromLocation(lastKnownLocation.latitude, lastKnownLocation.longitude)

                        }
                    }
                }
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                    mMap.isMyLocationEnabled = true
                    getDeviceLocation()
                }
            } else {
                // Permission denied, show a message to the user
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

}