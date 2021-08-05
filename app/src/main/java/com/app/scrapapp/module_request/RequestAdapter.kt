package com.app.scrapapp.module_request

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.module_account.Menu

class RequestAdapter(val context: Context, val clickListener: View.OnClickListener) : RecyclerView.Adapter<RequestViewHolder>() {

    val menus : ArrayList<Menu> = ArrayList()

    init {
        val titleAddress = context.resources.getString(R.string.title_address)
        val subTitleAddress = context.resources.getString(R.string.sub_title_address)
        menus.add(Menu(R.id.address,titleAddress,subTitleAddress))

        val titleRequests = context.resources.getString(R.string.title_requests)
        val subTitleRequests = context.resources.getString(R.string.sub_title_requests)
        menus.add(Menu(R.id.requests,titleRequests,subTitleRequests))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_request,parent,false)
        return RequestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
      /*  var menu : Menu = menus.get(position)
        holder.itemView.textViewTitle.text = menu.title
        holder.itemView.textViewSubTitle.text = menu.subTitle
        holder.itemView.setTag(R.string.tag_id,menu.id)
        holder.itemView.setOnClickListener(clickListener)*/
    }
}