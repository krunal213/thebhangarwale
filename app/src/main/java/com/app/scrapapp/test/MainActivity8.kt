package com.app.scrapapp.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.scrapapp.R
import kotlinx.android.synthetic.main.activity_main8.*


class MainActivity8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)
        var phoneNumber = intent.getStringExtra("phoneNumber")
        textViewPhoneNumber.text = phoneNumber
    }
}