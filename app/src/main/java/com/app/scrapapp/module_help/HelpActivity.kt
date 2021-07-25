package com.app.scrapapp.module_help

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import kotlinx.android.synthetic.main.activity_add_item_v2.*


class HelpActivity : AppCompatActivity(), View.OnClickListener {

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
        rv?.adapter = HelpAdapter(this,this)
        rv?.addItemDecoration(HelpItemDecoration(this))

    }

    override fun onClick(view : View?) {
        when(view?.id){
            R.id.layoutMain->{
                val id = view.getTag(R.string.tag_id)
                when(id){
                    R.id.contact_us->{
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:0123456789")
                        startActivity(intent)
                    }
                    R.id.email_us->{
                        val intent = Intent(Intent.ACTION_SENDTO)
                        intent.data = Uri.parse("mailto:help@bhangarwale.com")
                        startActivity(intent)
                    }
                }
            }
        }
    }
}