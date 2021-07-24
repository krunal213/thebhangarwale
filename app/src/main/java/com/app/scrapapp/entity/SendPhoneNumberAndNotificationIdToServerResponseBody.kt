package com.app.scrapapp.entity

import com.app.scrapapp.entity.contract.BhangarwaleResponseBody
import com.app.scrapapp.entity.contract.ResponseCodeBody

class SendPhoneNumberAndNotificationIdToServerResponseBody(
    responseCodeBody: ResponseCodeBody?
) : BhangarwaleResponseBody<Any?>(responseCodeBody, null)