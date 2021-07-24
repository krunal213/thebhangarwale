package com.app.scrapapp.module_address.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.scrapapp.R

class UpdateAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_address)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = resources.getString(R.string.title_update_address)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }
}