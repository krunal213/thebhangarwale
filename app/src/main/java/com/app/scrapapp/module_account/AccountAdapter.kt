package com.app.scrapapp.module_account

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import kotlinx.android.synthetic.main.adapter_notification.view.*

data class Menu(val id : Int,val title : String,val subTitle : String)

class AccountAdapter(val context: Context, val clickListener: View.OnClickListener) : RecyclerView.Adapter<AccountViewHolder>() {

    val menus : ArrayList<Menu> = ArrayList()

    init {
        val titleAddress = context.resources.getString(R.string.title_address)
        val subTitleAddress = context.resources.getString(R.string.sub_title_address)
        menus.add(Menu(R.id.address,titleAddress,subTitleAddress))

        val titleRequests = context.resources.getString(R.string.title_requests)
        val subTitleRequests = context.resources.getString(R.string.sub_title_requests)
        menus.add(Menu(R.id.requests,titleRequests,subTitleRequests))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_account,parent,false)
        return AccountViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        var menu : Menu = menus.get(position)
        holder.itemView.textViewTitle.text = menu.title
        holder.itemView.textViewSubTitle.text = menu.subTitle
        holder.itemView.setTag(R.string.tag_id,menu.id)
        holder.itemView.setOnClickListener(clickListener)
    }
}