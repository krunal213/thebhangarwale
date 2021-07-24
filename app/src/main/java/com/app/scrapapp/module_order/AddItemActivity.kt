package com.app.scrapapp.module_order

import android.app.Activity
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.ImageDetailScreenActivity
import com.app.scrapapp.R
import com.app.scrapapp.custom.constants.IMAGE_LIST
import com.app.scrapapp.custom.constants.MIME_TYPE_FOR_IMAGE
import com.app.scrapapp.custom.constants.RequestCode
import com.app.scrapapp.custom.constants.RequestCode.REQUEST_IMAGE_GET
import com.app.scrapapp.custom.widget.BhangarwaleLoginControllerActivity
import com.app.scrapapp.entity.Item
import com.app.scrapapp.module_login.PhoneNumberActivityV3
import com.app.scrapapp.module_order.adapter.AddItemAdapter
import com.app.scrapapp.module_order.adapter.ItemAdapter
import com.app.scrapapp.module_order.itemdecoration.AddItemDecoration
import kotlinx.android.synthetic.main.activity_add_item.*
import kotlinx.android.synthetic.main.content_app_bar.*

class AddItemActivity : BhangarwaleLoginControllerActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_add_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        recyclerviewImages.layoutManager =
            GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        recyclerviewImages.addItemDecoration(AddItemDecoration())
        recyclerviewImages.adapter = AddItemAdapter(this)
        imageViewGallery.setOnClickListener(this)
        imageViewDelete.setOnClickListener(this)
        var items: ArrayList<Item> = ArrayList()
        items.add(Item("Book", "Rs. 40/Kg"))
        items.add(Item("Plastic", "Rs. 20/Kg"))
        /*spinnerItems.adapter = ItemAdapter(
            this, ArrayList<Item>(
                items
            )
        )*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.imageViewGallery -> {
                selectImage()
            }
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
            R.id.imageViewDelete -> {
                val addItemAdapter: AddItemAdapter? = recyclerviewImages.adapter as AddItemAdapter?
                val intent: Intent = Intent(this, DeleteImagesActivity::class.java)
                intent.putExtra(IMAGE_LIST, addItemAdapter?.imageList)
                val options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        recyclerviewImages as RecyclerView,
                        "recyclerviewImages"
                    )
                startActivity(intent, options.toBundle())
            }
        }
    }

    fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = MIME_TYPE_FOR_IMAGE
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET)
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



}
