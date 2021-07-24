package com.app.scrapapp.module_login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.scrapapp.R
import com.app.scrapapp.custom.constants.Login
import com.app.scrapapp.custom.widget.ProgressBarDialogFragment
import com.app.scrapapp.module_home.view.activity.MainActivity
import com.mukesh.OnOtpCompletionListener
import kotlinx.android.synthetic.main.activity_otp.*

class OtpActivity : AppCompatActivity(),OnOtpCompletionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_v2)
        otp_view?.setOtpCompletionListener(this)



    }

    override fun onOtpCompleted(otp: String?) {
        val dialogFragment: ProgressBarDialogFragment = ProgressBarDialogFragment()
        dialogFragment.isCancelable = false
        dialogFragment.show(supportFragmentManager, "")
        //progressBar?.visibility = View.VISIBLE
        Thread {
            kotlin.run {
                Thread.sleep(5000)
                runOnUiThread(Runnable {
                    Login.IsLogin = true
                    val otpIntent: Intent = Intent(this@OtpActivity, MainActivity::class.java)
                    otpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    otpIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    this@OtpActivity.startActivity(otpIntent)
                })
            }
        }.start()
    }
}


