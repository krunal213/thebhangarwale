package com.app.scrapapp.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.scrapapp.R
import com.app.scrapapp.validators.OtpValidator
import kotlinx.android.synthetic.main.activity_main7.*
import kotlinx.android.synthetic.main.activity_main7.buttonLogin

class MainActivity7 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)
        buttonLogin.setOnClickListener(this)

        var phoneNumber = intent.getStringExtra("phoneNumber")
        textViewPhoneNumber.text = "Your Phone Number is"+"\t"+phoneNumber
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonLogin->{
                try {
                    val otp = editTextOTP.text.toString().trim()
                    if(OtpValidator.isEmptyOtp(otp)){
                        throw Exception(getString(R.string.title_otp_is_empty_error))
                    }
                    if(OtpValidator.isNotValidOtp(otp)){
                        throw Exception(getString(R.string.title_otp_is_valid_error))
                    }
                    val intentOtp = Intent(this,MainActivity8::class.java)
                    intentOtp.putExtra("phoneNumber",intent.getStringExtra("phoneNumber"))
                    startActivity(intentOtp)
                }catch (exception : Exception){
                    editTextOTP.error = exception.message
                }
            }
        }
    }
}