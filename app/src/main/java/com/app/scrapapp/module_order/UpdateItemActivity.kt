package com.app.scrapapp.module_order

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.app.scrapapp.R
import com.app.scrapapp.module_order.adapter.AddItemAdapter
import com.app.scrapapp.module_order.adapter.UpdateItemAdapter
import com.app.scrapapp.module_order.itemdecoration.AddItemDecoration
import com.app.scrapapp.module_order.itemdecoration.UpdateItemDecoration
import kotlinx.android.synthetic.main.activity_update_item.*

class UpdateItemActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_item)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_update_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)


        recyclerviewImages.layoutManager =
            GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        recyclerviewImages.addItemDecoration(UpdateItemDecoration())
        recyclerviewImages.adapter = UpdateItemAdapter(this)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu_add_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(v: View?) {

    }
}