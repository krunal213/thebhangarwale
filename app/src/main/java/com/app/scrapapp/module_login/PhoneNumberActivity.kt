package com.app.scrapapp.module_login

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.app.scrapapp.R
import com.app.scrapapp.custom.observer.BhangarwaleObserver
import com.app.scrapapp.custom.widget.ProgressBarDialogFragment
import com.app.scrapapp.databinding.ActivityPhoneNumberBinding
import com.app.scrapapp.entity.SendPhoneNumberAndNotificationIdToServerResponseBody
import com.app.scrapapp.module_login.viewmodel.PhoneNumberViewModel


class PhoneNumberActivity : AppCompatActivity(), View.OnClickListener {

    private var activityPhoneNumberBinding: ActivityPhoneNumberBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPhoneNumberBinding = DataBindingUtil.setContentView(this, R.layout.activity_phone_number)
        activityPhoneNumberBinding?.lifecycleOwner = this
        activityPhoneNumberBinding?.phoneNumberViewModel = PhoneNumberViewModel()
        activityPhoneNumberBinding?.rippleViewContinue?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.rippleViewContinue->{
                sendPhoneNumberAndNotificationIdToServer()
            }
        }
    }

    /*
     * validation and api call
     * for sending data to server
     *  */
    private fun sendPhoneNumberAndNotificationIdToServer() {
        activityPhoneNumberBinding
            ?.phoneNumberViewModel
            ?.sendPhoneNumberAndNotificationIdToServer()
            ?.observe(this,object :
                BhangarwaleObserver<SendPhoneNumberAndNotificationIdToServerResponseBody>() {
                override fun onPreExecute() {
                    val dialogFragment: ProgressBarDialogFragment = ProgressBarDialogFragment()
                    dialogFragment.isCancelable = false
                    dialogFragment.show(supportFragmentManager,ProgressBarDialogFragment.TAG)
                }
                override fun onSuccess(t: SendPhoneNumberAndNotificationIdToServerResponseBody?) {
                    val dialogFragment: ProgressBarDialogFragment = supportFragmentManager
                        .findFragmentByTag(ProgressBarDialogFragment.TAG) as ProgressBarDialogFragment
                    dialogFragment.dismiss()
                    this@PhoneNumberActivity.startActivity(Intent(this@PhoneNumberActivity,OtpActivity::class.java))
                }
                override fun onFailure(t: SendPhoneNumberAndNotificationIdToServerResponseBody?) {
                    val dialogFragment: ProgressBarDialogFragment = supportFragmentManager
                        .findFragmentByTag(ProgressBarDialogFragment.TAG) as ProgressBarDialogFragment
                    dialogFragment.dismiss()
                    this@PhoneNumberActivity.startActivity(Intent(this@PhoneNumberActivity,OtpActivity::class.java))
                }
            })
    }

}
