package com.app.scrapapp.test

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.text.style.CharacterStyle
import android.text.style.StyleSpan
import android.transition.Explode
import android.view.Menu
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.scrapapp.R
import com.app.scrapapp.custom.adapters.SearchViewAdapter
import com.app.scrapapp.custom.constants.SUGGESTED_SELECTED_ADDRESS
import com.app.scrapapp.module_address.view.adapter.AutoCompletePredictionAdapter
import com.ferfalk.simplesearchview.SimpleSearchView
import com.ferfalk.simplesearchview.utils.DimensUtils
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.android.synthetic.main.activity_explode_transition_test_actvity.*
import java.io.IOException

class ExplodeTransitionTestActvity : AppCompatActivity(), SimpleSearchView.OnQueryTextListener,
    View.OnClickListener {

    val EXTRA_REVEAL_CENTER_PADDING = 40
    var geocoder: Geocoder? = null
    var autoCompletePredictionAdapter: AutoCompletePredictionAdapter? = null
    var suggestions : ArrayList<Address> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explode_transition_test_actvity)
        setSupportActionBar(toolbar)
        toolbar.title = "Save Address"

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val enterTransition = Explode()
        enterTransition.duration = 300
        window.enterTransition = enterTransition


        // Initialize the SDK
        Places.initialize(this, "AIzaSyDXHLSlH9vdX-BSU0rU3g3eTyFFUBTi8-g")
        val appCompatActivity: AppCompatActivity = this

        // Create a new Places client instance
        val placesClient : PlacesClient = Places.createClient(this)
        val suggestions: ArrayList<String> = ArrayList()
        val STYLE_BOLD: CharacterStyle = StyleSpan(Typeface.BOLD)

        autoCompletePredictionAdapter =
            AutoCompletePredictionAdapter(this)
        recyclerviewAddress.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerviewAddress.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recyclerviewAddress.adapter = autoCompletePredictionAdapter

        geocoder = Geocoder(this)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search_address, menu)
        val item = menu.findItem(R.id.action_search_address)
        searchView.setMenuItem(item)
        val revealCenter = searchView.revealAnimationCenter
        revealCenter.x -= DimensUtils.convertDpToPx(EXTRA_REVEAL_CENTER_PADDING, this)
        searchView.showSearch(false)
        searchView.setOnQueryTextListener(this)
        searchView.setOnSearchViewListener(object : SearchViewAdapter() {
            override fun onSearchViewClosed() {
                this@ExplodeTransitionTestActvity.onBackPressed()
            }
            override fun onSearchViewClosedAnimation() {
                this@ExplodeTransitionTestActvity.onBackPressed()
            }
        })
        return true

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(searchString: String?): Boolean {
        progressBar.visibility = View.VISIBLE
        synchronized(Any()) {
            object : Thread() {
                override fun run() {
                    super.run()
                    try {
                        val address: ArrayList<Address>? = geocoder?.getFromLocationName(
                            searchString.toString(),
                            10
                        ) as? ArrayList<Address>
                        println("Address : "+address)
                        if (address != null) {
                            suggestions.clear()
                            for (address in address) {
                                suggestions.add(address)
                            }
                            this@ExplodeTransitionTestActvity.runOnUiThread {
                                autoCompletePredictionAdapter?.addAll(suggestions)
                                progressBar.visibility = View.GONE
                            }
                        }
                    } catch (e: IOException) {
                    }
                }
            }.start()
            return true
        }
    }

    override fun onQueryTextCleared(): Boolean {
        progressBar.visibility = View.GONE
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.constraintLayoutAddress->{
                var suggestedSelectedAddress : Address? = v.getTag(R.string.tag_suggestion) as Address?
                var intent = Intent()
                intent.putExtra(SUGGESTED_SELECTED_ADDRESS,suggestedSelectedAddress)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }

    }

}