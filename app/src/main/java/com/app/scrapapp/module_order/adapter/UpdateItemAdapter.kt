package com.app.scrapapp.module_order.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.module_order.viewholder.AddItemViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_update_item.view.*

class UpdateItemAdapter(val onClickListener: View.OnClickListener?) : RecyclerView.Adapter<AddItemViewHolder>() {

    val imageList : ArrayList<Uri?> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val dm = parent.context.getResources().getDisplayMetrics()
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_update_item, parent, false)
        /* view.layoutParams.width = dm.widthPixels/3
         view.layoutParams.height = dm.widthPixels/3
         */return AddItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        //val imageUri : Uri? = imageList.get(position)
        Glide.with(holder.itemView.imageViewForItems).load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg").into(holder.itemView.imageViewForItems)
       // holder.itemView.imageViewForItems.setTag(R.string.tag_image_uri,imageUri)
        holder.itemView.imageViewForItems.setOnClickListener(onClickListener)
    }

    fun notifyDataSetChanged(fullPhotoUri: Uri?) {
        if(imageList.size<=3) {
            imageList.add(fullPhotoUri)
            notifyDataSetChanged()
        }
    }
}