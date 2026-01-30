package com.example.simplelocationtrackerapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var statusText: TextView
    private lateinit var updateCounter: TextView
    private lateinit var latitudeValue: TextView
    private lateinit var longitudeValue: TextView
    private lateinit var accuracyValue: TextView
    private lateinit var lastUpdateTime: TextView
    private lateinit var mapView: MapView
    private var currentMarker: Marker? = null
    private val handler = Handler(Looper.getMainLooper())
    
    // Demo location (Manila, Philippines)
    private var currentLat = 14.5995
    private var currentLng = 120.9842
    private var updateCount = 0
    
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize OSMDroid configuration
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        
        setContentView(R.layout.activity_main)

        // Initialize views
        statusText = findViewById(R.id.statusText)
        updateCounter = findViewById(R.id.updateCounter)
        latitudeValue = findViewById(R.id.latitudeValue)
        longitudeValue = findViewById(R.id.longitudeValue)
        accuracyValue = findViewById(R.id.accuracyValue)
        lastUpdateTime = findViewById(R.id.lastUpdateTime)
        mapView = findViewById(R.id.mapView)
        
        val refreshButton: FloatingActionButton = findViewById(R.id.refreshButton)
        
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Setup OpenStreetMap
        setupMap()

        // Button click
        refreshButton.setOnClickListener {
            Toast.makeText(this, R.string.location_refreshed, Toast.LENGTH_SHORT).show()
            updateLocation()
        }

        // Request permission first
        if (!hasLocationPermission()) {
            requestLocationPermission()
        } else {
            startDemo()
        }
    }
    
    private fun setupMap() {
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(15.0)
        
        // Set initial position
        val startPoint = GeoPoint(currentLat, currentLng)
        mapView.controller.setCenter(startPoint)
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        statusText.text = getString(R.string.requesting_permission)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun startDemo() {
        Toast.makeText(this, R.string.tracking_started, Toast.LENGTH_SHORT).show()
        statusText.text = getString(R.string.tracking_active)
        updateLocation()
        
        // Auto-update every 3 seconds
        handler.postDelayed(object : Runnable {
            override fun run() {
                updateLocation()
                handler.postDelayed(this, 3000)
            }
        }, 3000)
    }

    private fun updateLocation() {
        updateCount++
        
        // Simulate movement
        currentLat += (Math.random() - 0.5) * 0.001
        currentLng += (Math.random() - 0.5) * 0.001
        
        val accuracy = 10.0f + (Math.random() * 5).toFloat()
        
        // Get current time
        val currentTime = java.text.SimpleDateFormat("hh:mm:ss a", java.util.Locale.getDefault())
            .format(java.util.Date())
        
        // Animate value changes
        animateTextChange(updateCounter, "#$updateCount")
        animateTextChange(latitudeValue, "%.6f".format(currentLat))
        animateTextChange(longitudeValue, "%.6f".format(currentLng))
        animateTextChange(accuracyValue, "%.1f m".format(accuracy))
        lastUpdateTime.text = "Last updated: $currentTime"

        // Update map with marker
        val geoPoint = GeoPoint(currentLat, currentLng)
        
        // Remove old marker
        currentMarker?.let { mapView.overlays.remove(it) }
        
        // Add new marker
        currentMarker = Marker(mapView).apply {
            position = geoPoint
            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            title = "Location #$updateCount"
            snippet = "Lat: ${"%.6f".format(currentLat)}, Lng: ${"%.6f".format(currentLng)}"
        }
        mapView.overlays.add(currentMarker)
        
        // Animate to new position
        mapView.controller.animateTo(geoPoint)
        mapView.invalidate()
    }
    
    private fun animateTextChange(textView: TextView, newText: String) {
        textView.animate()
            .alpha(0f)
            .setDuration(150)
            .withEndAction {
                textView.text = newText
                textView.animate()
                    .alpha(1f)
                    .setDuration(150)
                    .start()
            }
            .start()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.permission_granted, Toast.LENGTH_SHORT).show()
                startDemo()
            } else {
                Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show()
                startDemo()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
