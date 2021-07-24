package com.app.scrapapp.module_home.view.home

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.app.scrapapp.R
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_test4.*
import kotlinx.android.synthetic.main.content_app_bar.*

class ItemDetailScreenActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {


        // Enable Activity Transitions. Optionally enable Activity transitions in your
        // theme with <item name=”android:windowActivityTransitions”>true</item>.
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        // Set the transition name, which matches Activity A’s start view transition name, on
        // the root view.
        // findViewById<View>(android.R.id.content).transitionName = "container"

        // Attach a callback used to receive the shared elements from Activity A to be
        // used by the container transform transition.
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)


        // Set this Activity’s enter and return transition to a MaterialContainerTransform
        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(item)
            setAllContainerColors(Color.TRANSPARENT)
            setFadeMode(MaterialContainerTransform.FADE_MODE_THROUGH)
            //setFadeMode(FADE_MODE_OUT)
            setScrimColor(Color.TRANSPARENT)
            duration = 300L
        }
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(item)
            setAllContainerColors(Color.TRANSPARENT)
            //setFadeMode(FADE_MODE_IN)
            setFadeMode(MaterialContainerTransform.FADE_MODE_THROUGH)
            setScrimColor(Color.TRANSPARENT)
            duration = 250L
        }

        item.transitionName = "shared_element_container"

        /*setSupportActionBar(toolbar)
        supportActionBar?.title = "Update Item"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)*/

    }

}
