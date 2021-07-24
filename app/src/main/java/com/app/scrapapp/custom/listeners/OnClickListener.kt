package com.app.scrapapp.custom.listeners

import android.view.View
import java.util.concurrent.atomic.AtomicBoolean

class OnClickListener(
    private val clickListener: View.OnClickListener,
    private val intervalMs: Long = 0L
) : View.OnClickListener {

    private var canClick = AtomicBoolean(true)

    override fun onClick(v: View?) {
        clickListener.onClick(v)
        /*if (canClick.getAndSet(false)) {
            v?.run {
                postDelayed({
                    canClick.set(true)
                }, intervalMs)
                clickListener.onClick(v)
            }
        }*/
    }
}