package com.app.scrapapp.module_checkout

import android.location.Address
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.scrapapp.R
import com.app.scrapapp.module_home.view.home.HomeItemDecoration
import kotlinx.android.synthetic.main.activity_confirmation.*
import kotlinx.android.synthetic.main.content_selected_address.*
import java.util.*
import kotlin.collections.ArrayList

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_bhangar_request)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val myaddress = ArrayList<Address>()
        val address = Address(Locale.ENGLISH)
        address.setAddressLine(0,"4D, Harish Arjun Palay Marg, New Patra Chawl, Dhaku Prabhuchi Wadi, Byculla East, Byculla, Mumbai, Maharashtra 400033, India")
        address.latitude = 18.98307494631155
        address.longitude = 72.83906176686287
        myaddress.add(address)

        /*val myAddressAdapter = SelectedAddressAdapter(myaddress)
        recyclerviewAddress.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        recyclerviewAddress.adapter = myAddressAdapter
        val dividerItemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.drawable_horizonatal_divider)?.let {
            dividerItemDecoration.setDrawable(
                it
            )
        }
        recyclerviewAddress.addItemDecoration(dividerItemDecoration)*/

        textViewAddress.text = "4D, Harish Arjun Palay Marg, New Patra Chawl, Dhaku Prabhuchi Wadi, Byculla East, Byculla, Mumbai, Maharashtra 400033, India"

        recyclerviewBhangarItems.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerviewBhangarItems.adapter = ItemAdapter()

        val dividerItemDecorationItem = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.drawable_horizonatal_divider)?.let {
            dividerItemDecorationItem.setDrawable(
                it
            )
        }
        recyclerviewBhangarItems.addItemDecoration(dividerItemDecorationItem)



    }

}