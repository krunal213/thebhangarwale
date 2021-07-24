package com.app.scrapapp.entity.contract

class ResponseCodeBody(var responseCode: ResponseCode?, var message: String?=null) {
    enum class ResponseCode {
        SUCCESS, FAIL, LOADING
    }
}

