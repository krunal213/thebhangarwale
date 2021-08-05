package com.app.scrapapp.module_home.view.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.module_account.Menu
import com.app.scrapapp.module_request.RequestViewHolder

class NotificationAdapter() : RecyclerView.Adapter<NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_notifications,parent,false)
        return NotificationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 19
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        /*  var menu : Menu = menus.get(position)
          holder.itemView.textViewTitle.text = menu.title
          holder.itemView.textViewSubTitle.text = menu.subTitle
          holder.itemView.setTag(R.string.tag_id,menu.id)
          holder.itemView.setOnClickListener(clickListener)*/
    }
}