package com.app.scrapapp.custom.listeners

interface IFragmentListener {
    fun <T> notify(t: T)
}