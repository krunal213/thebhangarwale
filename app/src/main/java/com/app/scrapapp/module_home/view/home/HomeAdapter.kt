package com.app.scrapapp.module_home.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.ui.home.HomeViewHolder
import com.app.scrapapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.adapter_home_v8.view.*

class HomeAdapter(
    val appCompatActivity: AppCompatActivity,
    val longClickListener: View.OnLongClickListener,
    val clickListener: View.OnClickListener

) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_home_v9,parent,false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5;
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.itemView.setOnLongClickListener(longClickListener)
        holder.itemView.setOnClickListener(clickListener)
        Glide.with(appCompatActivity)
            .load(R.drawable.download)
            .transform(CenterCrop(), RoundedCorners(8))
            .into(holder.itemView.image)
    }
}