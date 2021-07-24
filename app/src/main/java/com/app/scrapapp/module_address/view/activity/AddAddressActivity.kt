package com.app.scrapapp.module_address.view.activity

import android.annotation.SuppressLint
import android.content.res.Resources
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.app.scrapapp.R
import com.app.scrapapp.custom.constants.EDIT_AVAILABLE_ADDRESS
import com.app.scrapapp.custom.listeners.IActivityListener
import com.app.scrapapp.custom.listeners.IFragmentListener
import com.app.scrapapp.module_address.view.fragment.AddAddressFragment
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.rodolfonavalon.shaperipplelibrary.model.Circle
import kotlinx.android.synthetic.main.activity_add_address.*
import java.util.*

/**this screem helps to find address
 * using map create address by moving next screen
 * with create address button
 * and save address
 ***/
class AddAddressActivity : AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnCameraIdleListener,
    View.OnClickListener,
    IFragmentListener,
    GoogleMap.OnCameraMoveListener {

    private lateinit var mMap: GoogleMap
    private lateinit var geocoder: Geocoder
    private val iActivityListener: IActivityListener = AddAddressFragment()
    private var editAvailableAddress : Address? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        editAvailableAddress = intent.getParcelableExtra<Address>(EDIT_AVAILABLE_ADDRESS)

        //init toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //init address fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.address, iActivityListener as Fragment)
            .commit()

        //init map fragment
        val mapFragment = SupportMapFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.map, mapFragment)
            .commit()

        //map will open asynchronus way
        mapFragment.getMapAsync(this)

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //assign style to a map
        try {
            val success = mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.style_json
                )
            )
        } catch (e: Resources.NotFoundException) {

        }


        if(editAvailableAddress!=null){
            val nagpur = editAvailableAddress?.latitude?.let { editAvailableAddress?.longitude?.let { it1 ->
                LatLng(it,
                    it1
                )
            } }
            updateMapWithLatLong(nagpur,mMap)
        }else{
            //fetching current location of device
            var mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            mFusedLocationClient.lastLocation.addOnCompleteListener { task ->
                val location: Location? = task.getResult()
                if(location!=null){
                    //set lat long for showing in ui
                    //init all component for user
                    val nagpur = LatLng(location?.latitude, location?.longitude)
                    updateMapWithLatLong(nagpur,mMap)
                }
            }
        }



    }

    private fun updateMapWithLatLong(
        latLng: LatLng?,
        mMap: GoogleMap
    ) {

        val cameraPosition: CameraPosition = CameraPosition.builder()
            .target(latLng)
            .zoom(15f)
            .build()
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        mMap.setOnCameraIdleListener(this@AddAddressActivity)
        mMap.setOnCameraMoveListener(this@AddAddressActivity)
        geocoder = Geocoder(this@AddAddressActivity)
        Thread {
            kotlin.run {
                //transition animation triggering
                Thread.sleep(500)
                this@AddAddressActivity.runOnUiThread {
                    motionLayout.transitionToEnd()
                    //ripple effect initializing in surrounding of marker
                    ripple.rippleDuration = 5757
                    ripple.rippleCount = 1
                    ripple.rippleMaximumRadius = 350f
                    ripple.isEnableSingleRipple = true
                    ripple.isEnableColorTransition = true
                    ripple.setRippleColor(resources.getColor(R.color.colorAccent))
                    ripple.rippleShape = Circle()
                    textViewNagpur.setOnClickListener(this@AddAddressActivity)
                }
            }
        }.start()
    }


    /**whenever we stop moving camera
     * this method is called and we get location
     * and pass my location textview
     * and start ripple again
     * after movement
     * **/
    override fun onCameraIdle() {
        var latLng: LatLng = mMap.cameraPosition.target
        var list: ArrayList<Address>? =
            geocoder.getFromLocation(
                latLng.latitude,
                latLng.longitude,
                1) as? ArrayList<Address>
        var size = list?.size
        if (size != null && size > 0) {
            var address: Address? = list?.get(0)
            iActivityListener.notify(address?.getAddressLine(0))
        }
        ripple.startRipple()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textViewNagpur -> {
                val nagpur = LatLng(21.1458, 79.0882)
                val cameraPosition: CameraPosition = CameraPosition.builder()
                    .target(nagpur)
                    .zoom(15f)
                    .build()
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return true
    }

    override fun <T> notify(t: T) {
        when(t){
            is Address->{
                 val nagpur = LatLng(t.latitude, t.longitude)
                val cameraPosition: CameraPosition = CameraPosition.builder()
                    .target(nagpur)
                    .zoom(15f)
                    .build()
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }
        }
    }

    override fun onCameraMove() {
        ripple.stopRipple()
    }

}
