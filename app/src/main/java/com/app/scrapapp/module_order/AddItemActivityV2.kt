package com.app.scrapapp.module_order

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.app.scrapapp.ImageDetailScreenActivity
import com.app.scrapapp.R
import com.app.scrapapp.custom.constants.MIME_TYPE_FOR_IMAGE
import com.app.scrapapp.custom.constants.RequestCode
import com.app.scrapapp.custom.widget.BhangarwaleLoginControllerActivity
import com.app.scrapapp.entity.Item
import com.app.scrapapp.module_order.adapter.AddItemAdapter
import com.app.scrapapp.module_order.adapter.ItemAdapter
import com.app.scrapapp.module_order.itemdecoration.AddItemDecoration
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.android.synthetic.main.activity_add_item_v2.*

class AddItemActivityV2 : BhangarwaleLoginControllerActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_v2)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_add_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        recyclerviewImages.layoutManager =
            GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        recyclerviewImages.addItemDecoration(AddItemDecoration())
        recyclerviewImages.adapter = AddItemAdapter(this)


        var items: ArrayList<Item> = ArrayList()
        items.add(Item("Book", "Rs.40/Kg"))
        items.add(Item("Plastic", "Rs.20/Kg"))
        spinnerItems.setAdapter(
            ItemAdapter(this,items)
        )
        
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu_add_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_gallery -> {
                selectImage()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = MIME_TYPE_FOR_IMAGE
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, RequestCode.REQUEST_IMAGE_GET)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val thumbnail: Bitmap? = data?.getParcelableExtra("data")
                val fullPhotoUri: Uri? = data?.data
                val addItemAdapter: AddItemAdapter? = recyclerviewImages.adapter as AddItemAdapter?
                addItemAdapter?.notifyDataSetChanged(fullPhotoUri)
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.imageViewForItems -> {
                val intent: Intent = Intent(this, ImageDetailScreenActivity::class.java)
                intent.data = view.getTag(R.string.tag_image_uri) as Uri?
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    view as View,
                    "imageViewForItems"
                )
                startActivity(intent, options.toBundle())
            }
        }

    }

}