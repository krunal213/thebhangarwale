package com.app.scrapapp.module_account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.module_address.view.activity.MyAddressActivity
import com.app.scrapapp.module_home.view.notifications.ProfileAdapter
import com.app.scrapapp.module_home.view.notifications.ProfileItemDecoration
import com.app.scrapapp.module_profile.view.itemdecoration.AccountItemDecoration
import com.app.scrapapp.module_request.RequestActivity
import kotlinx.android.synthetic.main.activity_add_item_v2.*

class AccountActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_account)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val rv : RecyclerView = findViewById<RecyclerView>(R.id.recyclerviewItems)
        rv?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        rv?.adapter = AccountAdapter(this,this)
        rv?.addItemDecoration(AccountItemDecoration(this))

    }

    override fun onClick(view : View?) {
        when(view?.id){
            R.id.layoutMain->{
                val id = view.getTag(R.string.tag_id)
                when(id){
                    R.id.address->{
                        startActivity(Intent(this,MyAddressActivity::class.java))
                    }
                    R.id.requests->{
                        startActivity(Intent(this,RequestActivity::class.java))
                    }
                }
            }
        }
    }
}