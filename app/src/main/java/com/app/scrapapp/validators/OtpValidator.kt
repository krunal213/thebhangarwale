package com.app.scrapapp.validators

object OtpValidator {

    fun isEmptyOtp(otp: String): Boolean {
        return otp.trim().isEmpty()
    }

    fun isNotValidOtp(otp: String): Boolean {
        return otp.trim().length != 4
    }

}
