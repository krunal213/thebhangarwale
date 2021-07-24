package com.app.scrapapp.module_order

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.app.scrapapp.R
import com.app.scrapapp.custom.constants.IMAGE_LIST
import com.app.scrapapp.module_order.adapter.DeleteItemAdapter
import com.app.scrapapp.module_order.itemdecoration.DeleteItemDecoration
import kotlinx.android.synthetic.main.activity_delete_images.*
import kotlinx.android.synthetic.main.content_app_bar.*

class DeleteImagesActivity : AppCompatActivity(), View.OnClickListener {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_images)

        val imageList : ArrayList<Uri>? = intent.getParcelableArrayListExtra<Uri>(IMAGE_LIST)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_delete_images)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        recyclerviewImages.layoutManager = GridLayoutManager(this,3,
            GridLayoutManager.VERTICAL,false)
        recyclerviewImages.addItemDecoration(DeleteItemDecoration())
        recyclerviewImages.adapter = DeleteItemAdapter(this)


        var deleteItemAdapter : DeleteItemAdapter = recyclerviewImages.adapter as DeleteItemAdapter
        deleteItemAdapter.notifyAddDataSetThreeAndLessThanThreeChanged(imageList)


        val enterTransition = Explode()
        enterTransition.duration = 300
        window.enterTransition = enterTransition




    }

    override fun onClick(v: View?) {

    }


    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }
}