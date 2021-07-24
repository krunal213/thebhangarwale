package com.app.scrapapp.module_login

import android.app.Activity
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.scrapapp.R
import com.app.scrapapp.custom.widget.ProgressBarDialogFragment
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_phone_number_v3.*

class PhoneNumberActivityV3 : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    View.OnClickListener {
    private var mCredentialsApiClient: GoogleApiClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number_v3)
        mCredentialsApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .enableAutoManage(this, this)
            .addApi(Auth.CREDENTIALS_API)
            .build()
        val hintRequest = HintRequest.Builder()
            .setHintPickerConfig(
                CredentialPickerConfig.Builder()
                    .setShowCancelButton(true)
                    .build()
            )
            .setPhoneNumberIdentifierSupported(true)
            .build()
        val intent =
            Auth.CredentialsApi.getHintPickerIntent(mCredentialsApiClient, hintRequest)
        try {
            startIntentSenderForResult(
                intent.intentSender,
                RC_HINT,
                null,
                0,
                0,
                0
            )
        } catch (e: SendIntentException) {
        }
        rippleViewContinue.setOnClickListener(this)
    }
    override fun onConnected(bundle: Bundle?) {}
    override fun onConnectionSuspended(i: Int) {}
    override fun onConnectionFailed(connectionResult: ConnectionResult) {}
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                when (requestCode) {
                    RC_HINT -> {
                        val credential: Credential? = data?.getParcelableExtra(Credential.EXTRA_KEY)
                        if (credential != null) {
                            textInputEditTextPhoneNumber.setText(credential.id.substring(3, 13))
                        }
                    }
                }
            }
        }
    }
    companion object {
        val TAG = PhoneNumberActivity4::class.java.simpleName
        private const val RC_HINT = 1000
    }
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.rippleViewContinue->{
                val dialogFragment: ProgressBarDialogFragment = ProgressBarDialogFragment()
                dialogFragment.isCancelable = false
                dialogFragment.show(supportFragmentManager, ProgressBarDialogFragment.TAG)
                Handler().postDelayed(Runnable {
                    val intentOtp = Intent(this,OtpActivity::class.java)
                    startActivity(intentOtp)
                    dialogFragment.dismiss()
                }, 5000)

            }
        }
    }
}