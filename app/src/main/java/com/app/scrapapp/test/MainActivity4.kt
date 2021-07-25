package com.app.scrapapp.test

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.app.scrapapp.R
import com.app.scrapapp.module_address.view.fragment.UpdateAddressFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main4.*

class MainActivity4 : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment2,UpdateAddressFragment())
            .commit()




        val mapFragment = SupportMapFragment()




        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment1,mapFragment,"map")
            .commit()





        mapFragment.getMapAsync(this)




        //motionlayout.transitionToEnd()


        button.setOnClickListener {

        }
    }

    override fun onMapReady(mMap: GoogleMap?) {






        val nagpur = LatLng(21.146633, 79.088860)



       /* val marker = mMap?.addMarker(
            MarkerOptions()
                .position(nagpur)
        )*/


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap?.isMyLocationEnabled = true



        val cameraPosition: CameraPosition = CameraPosition.builder()
            .target(nagpur)
            .zoom(15f)
            .build()



        mMap?.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))



        Thread(){
            kotlin.run {
                Thread.sleep(2000)
            }
        }.start()


        motionlayout.transitionToEnd()



    }
}