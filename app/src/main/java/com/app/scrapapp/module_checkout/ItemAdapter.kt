package com.app.scrapapp.module_checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.ui.home.HomeViewHolder
import com.app.scrapapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.adapter_home_v8.view.*

class ItemAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_v2,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5;
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       Glide.with(holder.itemView.context)
            .load(R.drawable.download)
            .transform(CenterCrop(), RoundedCorners(8))
            .into(holder.itemView.image)
    }

}