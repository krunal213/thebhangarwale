package com.app.scrapapp.custom.widget

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.scrapapp.custom.constants.Login
import com.app.scrapapp.module_login.PhoneNumberActivityV3

open abstract class BhangarwaleLoginControllerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!Login.IsLogin){
            val intentLogin : Intent = Intent(this, PhoneNumberActivityV3::class.java)
            intentLogin.setFlags(FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intentLogin)
            finish()
        }
    }
}