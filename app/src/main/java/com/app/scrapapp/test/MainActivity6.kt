package com.app.scrapapp.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.scrapapp.R
import com.app.scrapapp.validators.PhoneNumberValidator
import kotlinx.android.synthetic.main.activity_main6.*

class MainActivity6 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        buttonLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        /*when(v?.id){
            R.id.buttonLogin->{
                try {
                    val phoneNumber = editTextPhoneNumber.text.toString().trim()
                    if(PhoneNumberValidator.isEmptyPhoneNumber(phoneNumber)){
                        throw Exception(getString(R.string.title_phone_number_is_empty_error))
                    }
                    if(PhoneNumberValidator.isNotValidPhoneNumber(phoneNumber)){
                        throw Exception(getString(R.string.title_phone_number_is_valid_error))
                    }
                    val intent = Intent(this,MainActivity7::class.java)
                    intent.putExtra("phoneNumber",phoneNumber)
                    startActivity(intent)
                }catch (exception : Exception){
                    editTextPhoneNumber.error = exception.message
                }

            }
        }*/
    }
}