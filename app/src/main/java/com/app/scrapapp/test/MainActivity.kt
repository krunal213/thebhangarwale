package com.app.scrapapp.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.app.scrapapp.R
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.libraries.places.widget.Autocomplete
import android.content.Intent
import com.google.android.libraries.places.api.model.Place
import java.util.*
import java.util.Arrays.asList



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Places.initialize(this, "AIzaSyDXHLSlH9vdX-BSU0rU3g3eTyFFUBTi8-g")
        val fields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS)


// Start the autocomplete intent.
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.OVERLAY, fields
        )
            .build(this)
        startActivityForResult(intent, 1)
    }
}
