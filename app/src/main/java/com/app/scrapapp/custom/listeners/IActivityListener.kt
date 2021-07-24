package com.app.scrapapp.custom.listeners

interface IActivityListener {
    fun <T> notify(t: T)
}