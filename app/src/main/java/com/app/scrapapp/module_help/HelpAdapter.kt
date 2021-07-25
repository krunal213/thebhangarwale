package com.app.scrapapp.module_help

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import kotlinx.android.synthetic.main.adapter_notification.view.*

data class Menu(val id : Int,val title : String,val subTitle : String)

class HelpAdapter(val context: Context, val clickListener: View.OnClickListener) : RecyclerView.Adapter<HelpViewHolder>() {

    val menus : ArrayList<Menu> = ArrayList()

    init {
        val titleContactUs = context.resources.getString(R.string.title_contact_us)
        val subTitleContactUs = context.resources.getString(R.string.sub_title_contact_us)
        menus.add(Menu(R.id.contact_us,titleContactUs,subTitleContactUs))

        val titleEmailUs = context.resources.getString(R.string.title_email_us)
        val subTitleEmailUs = context.resources.getString(R.string.sub_title_email_us)
        menus.add(Menu(R.id.email_us,titleEmailUs,subTitleEmailUs))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_help,parent,false)
        return HelpViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: HelpViewHolder, position: Int) {
        var menu : Menu = menus.get(position)
        holder.itemView.textViewTitle.text = menu.title
        holder.itemView.textViewSubTitle.text = menu.subTitle
        holder.itemView.setTag(R.string.tag_id,menu.id)
        holder.itemView.setOnClickListener(clickListener)
    }
}