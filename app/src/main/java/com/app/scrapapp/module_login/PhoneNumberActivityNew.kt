package com.app.scrapapp.module_login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.app.scrapapp.R
import com.app.scrapapp.databinding.ActivityPhoneNumberNewBinding
import kotlinx.android.synthetic.main.activity_phone_number_new.*

class PhoneNumberActivityNew : AppCompatActivity(), View.OnClickListener {

    private var activityPhoneNumberNewBinding : ActivityPhoneNumberNewBinding? = null
    private var validator : Validator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPhoneNumberNewBinding = DataBindingUtil.setContentView(this,R.layout.activity_phone_number_new)
        validator = Validator(activityPhoneNumberNewBinding)
        activityPhoneNumberNewBinding?.linearLayoutContinueNew?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.linearLayoutContinueNew->{
               //lin
                //textInputLayoutPhoneNumberNew.error = "Please enter your mobile number"

            }
        }
    }

}