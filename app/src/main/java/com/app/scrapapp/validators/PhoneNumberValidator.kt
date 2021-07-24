package com.app.scrapapp.validators

object PhoneNumberValidator {

    fun isNotValidPhoneNumber(phoneNumber: String?): Boolean? {
        return phoneNumber?.trim()?.length != 10
    }

    fun isEmptyPhoneNumber(phoneNumber: String?): Boolean? {
        return phoneNumber?.trim()?.isEmpty()
    }

}