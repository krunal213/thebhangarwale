package com.app.scrapapp.module_order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.app.scrapapp.R
import com.app.scrapapp.test.MainBhangarwaleActivity
import kotlinx.android.synthetic.main.content_headers.*

class CreateOrderActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_order)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_create_order)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        imageViewAdd.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.imageViewAdd->{
                val intent : Intent = Intent(this, AddItemActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        val intentHome : Intent = Intent(this, MainBhangarwaleActivity::class.java)
        intentHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intentHome)
        super.finish()
    }
}
