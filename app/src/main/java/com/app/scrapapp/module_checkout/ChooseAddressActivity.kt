package com.app.scrapapp.module_checkout

import android.content.Intent
import android.location.Address
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.scrapapp.R
import com.app.scrapapp.module_address.view.activity.AddAddressActivity
import com.app.scrapapp.module_address.view.adapter.MyAddressAdapter
import com.app.scrapapp.module_home.view.SmoothRefreshLayoutHeader
import com.app.scrapapp.module_home.view.home.HomeItemDecoration
import kotlinx.android.synthetic.main.activity_choose_address.*
import kotlinx.android.synthetic.main.activity_my_address.*
import kotlinx.android.synthetic.main.activity_my_address.recyclerviewAddress
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.mRefreshLayout
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import me.dkzwm.widget.srl.indicator.IIndicator
import java.util.*
import kotlin.collections.ArrayList

class ChooseAddressActivity : AppCompatActivity(), View.OnLongClickListener, View.OnClickListener {

    private val mHandler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_address)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_choose_address)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        //imageViewAdd.setOnClickListener(this)

        //18.98307494631155,72.83906176686287
        val myaddress = ArrayList<Address>()
        val address = Address(Locale.ENGLISH)
        address.setAddressLine(0,"4D, Harish Arjun Palay Marg, New Patra Chawl, Dhaku Prabhuchi Wadi, Byculla East, Byculla, Mumbai, Maharashtra 400033, India")
        address.latitude = 18.98307494631155
        address.longitude = 72.83906176686287

        myaddress.add(address)
        myaddress.add(address)
        myaddress.add(address)
        myaddress.add(address)

        val myAddressAdapter = ChooseAddressAdapter(myaddress,this,this)
        recyclerviewAddress.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        recyclerviewAddress.adapter = myAddressAdapter
        recyclerviewAddress.addItemDecoration(ChooseItemDecoration(this))

        val header = SmoothRefreshLayoutHeader<IIndicator>(this)
        mRefreshLayout.setHeaderView(header)

        mRefreshLayout.setOnRefreshListener(
            object : RefreshingListenerAdapter() {
                override fun onRefreshing() {
                    mHandler.postDelayed(
                        Runnable {
                            mRefreshLayout.refreshComplete()
                        },
                        2000
                    )
                }

                override fun onLoadingMore() {
                    mHandler.postDelayed(
                        Runnable {
                            mRefreshLayout.refreshComplete()
                        },
                        2000
                    )
                }
            })
        mRefreshLayout.autoRefresh(false)
        buttonSaveAddress.setOnClickListener(this)
    }

    override fun onLongClick(v: View?): Boolean {
        return false
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonSaveAddress->{
                val intentAddAddress = Intent(this, ConfirmationActivity::class.java)
                startActivity(intentAddAddress)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu_my_address,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_address->{
                val intentAddAddress = Intent(this, AddAddressActivity::class.java)
                startActivity(intentAddAddress)
            }
        }
        return true
    }

}