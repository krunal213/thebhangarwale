package com.app.scrapapp.module_home.view.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R

class MultipleImageAdapter() : RecyclerView.Adapter<MultipleImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_multiple_image,parent,false)
        var viewHolder = MultipleImageViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: MultipleImageViewHolder, position: Int) {




    }
}