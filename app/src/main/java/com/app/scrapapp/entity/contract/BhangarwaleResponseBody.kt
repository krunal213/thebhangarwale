package com.app.scrapapp.entity.contract

import java.io.Serializable

abstract class BhangarwaleResponseBody<Response> protected constructor(
    open var responseCodeBody: ResponseCodeBody?,
    open var response: Response?
) : Serializable