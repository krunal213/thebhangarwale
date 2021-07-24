package com.app.scrapapp.module_login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.scrapapp.custom.exception.EmptyPhoneNumberException
import com.app.scrapapp.custom.exception.NotValidPhoneNumberException
import com.app.scrapapp.entity.contract.ResponseCodeBody
import com.app.scrapapp.entity.SendPhoneNumberAndNotificationIdToServerResponseBody
import com.app.scrapapp.validators.PhoneNumberValidator

class PhoneNumberViewModel : ViewModel() {

    val phoneNumberLiveData = MutableLiveData<String>()
    val phoneNumberErrorResponseLiveData = MutableLiveData<String>()

    fun sendPhoneNumberAndNotificationIdToServer(): LiveData<SendPhoneNumberAndNotificationIdToServerResponseBody> {
        val sendPhoneNumberAndNotificationIdToServerResponseBodyLiveData =
            MutableLiveData<SendPhoneNumberAndNotificationIdToServerResponseBody>()
        try {
            val isEmptyPhoneNumber =
                PhoneNumberValidator.isEmptyPhoneNumber(phoneNumber = phoneNumberLiveData.value)
            if (isEmptyPhoneNumber == null || isEmptyPhoneNumber) {
                throw EmptyPhoneNumberException("Please Enter Phone Number")
            }
            val isNotValidPhoneNumber =
                PhoneNumberValidator.isNotValidPhoneNumber(phoneNumber = phoneNumberLiveData.value)
            if (isNotValidPhoneNumber == null || isNotValidPhoneNumber) {
                throw NotValidPhoneNumberException("Please Enter Valid Phone Number")
            }
            val serverResponseBody = SendPhoneNumberAndNotificationIdToServerResponseBody(
                ResponseCodeBody(
                    ResponseCodeBody.ResponseCode.LOADING
                )
            )
            sendPhoneNumberAndNotificationIdToServerResponseBodyLiveData.value = serverResponseBody
            Thread {
                kotlin.run {
                    Thread.sleep(5000)
                    serverResponseBody.responseCodeBody =
                        ResponseCodeBody(
                            ResponseCodeBody.ResponseCode.FAIL,
                            "Something went wrong!!"
                        )
                    sendPhoneNumberAndNotificationIdToServerResponseBodyLiveData.postValue(serverResponseBody)
                    phoneNumberErrorResponseLiveData.postValue(serverResponseBody?.responseCodeBody?.message)
                }
            }.start()
        }
        catch (exception: EmptyPhoneNumberException) {
            Thread {
                kotlin.run {
                    Thread.sleep(5000)
                    phoneNumberErrorResponseLiveData.postValue(exception.message)
                }
            }.start()
        }
        catch (exception: NotValidPhoneNumberException) {
            Thread {
                kotlin.run {
                    Thread.sleep(5000)
                    phoneNumberErrorResponseLiveData.postValue(exception.message)

                }
            }.start()
        }
        catch (exception: Exception) {
            Thread {
                kotlin.run {
                    Thread.sleep(5000)
                    phoneNumberErrorResponseLiveData.postValue("Something went wrong!!")

                }
            }.start()
        }
        finally {
            return sendPhoneNumberAndNotificationIdToServerResponseBodyLiveData
        }
    }

}