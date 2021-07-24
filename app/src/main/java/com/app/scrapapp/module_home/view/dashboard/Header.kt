package com.app.scrapapp.module_home.view.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.scrapapp.R
import me.dkzwm.widget.srl.SmoothRefreshLayout
import me.dkzwm.widget.srl.extra.IRefreshView
import me.dkzwm.widget.srl.indicator.IIndicator

class Header(var context : Context) : IRefreshView<IIndicator> {

    lateinit var viewObject : View

    override fun getView(): View {
        viewObject = LayoutInflater.from(context).inflate(R.layout.layout_progressbar,null)
        return viewObject
    }

    override fun onFingerUp(layout: SmoothRefreshLayout?, indicator: IIndicator?) {}

    override fun onPureScrollPositionChanged(layout: SmoothRefreshLayout?, status: Byte, indicator: IIndicator?) {}

    override fun getType(): Int {
        return IRefreshView.TYPE_HEADER.toInt()
    }

    override fun getStyle(): Int {
        return IRefreshView.STYLE_DEFAULT.toInt()
    }

    override fun getCustomHeight(): Int {
        return viewObject.layoutParams.height
    }

    override fun onRefreshComplete(layout: SmoothRefreshLayout?, isSuccessful: Boolean) {}

    override fun onReset(layout: SmoothRefreshLayout?) {}

    override fun onRefreshBegin(layout: SmoothRefreshLayout?, indicator: IIndicator?) {}

    override fun onRefreshPositionChanged(layout: SmoothRefreshLayout?, status: Byte, indicator: IIndicator?) {}

    override fun onRefreshPrepare(layout: SmoothRefreshLayout?) {}
}