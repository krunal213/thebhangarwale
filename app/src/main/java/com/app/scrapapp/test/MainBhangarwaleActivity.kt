package com.app.scrapapp.test

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.scrapapp.R
import kotlinx.android.synthetic.main.activity_main_bhangarwale.*

class MainBhangarwaleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bhangarwale)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)



        //val navController = findNavController(R.id.nav_host_fragment)


        //setupActionBarWithNavController(navController, null)

        //nav.setupWithNavController(navController)


    }
}