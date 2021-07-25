package com.app.scrapapp.module_settings

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.module_home.view.notifications.ProfileAdapter
import com.app.scrapapp.module_home.view.notifications.ProfileItemDecoration
import kotlinx.android.synthetic.main.activity_add_item_v2.*

class SettingsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        /*val rv : RecyclerView = findViewById<RecyclerView>(R.id.recyclerviewItems)
        rv?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        rv?.adapter = SettingsAdapter()
        rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                outRect.left = parent.resources.getDimensionPixelOffset(R.dimen.recyclerview_edges)
                outRect.right = parent.resources.getDimensionPixelOffset(R.dimen.recyclerview_edges)
            }
            private var mDivider: Drawable? = null
            init {
                mDivider = ContextCompat.getDrawable(this@SettingsActivity, R.drawable.drawable_horizonatal_divider)
            }
            override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                val dividerLeft = parent.left
                val dividerRight = parent.right
                val childCount = parent.childCount
                for (i in 0 until childCount - 1) {
                    val settingsAdapter : SettingsAdapter = parent.adapter as SettingsAdapter
                    if (settingsAdapter.settingsItems[i] is SettingItem.Header && i != 0){
                        val child = parent.getChildAt(i-1)
                        val params = child.layoutParams as RecyclerView.LayoutParams
                        val dividerTop = child.bottom + params.bottomMargin
                        val dividerBottom = dividerTop + mDivider!!.intrinsicHeight
                        mDivider!!.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
                        mDivider!!.draw(c)
                    }




                }
            }
        })*/


    }

}