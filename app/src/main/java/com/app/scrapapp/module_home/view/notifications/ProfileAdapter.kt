package com.app.scrapapp.module_home.view.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.ui.notifications.ProfileViewHolder
import com.app.scrapapp.R
import kotlinx.android.synthetic.main.adapter_notification.view.*

data class Menu(val id : Int,val icon : Int,val title : String,val subTitle : String)

class ProfileAdapter(val context: Context,val clickListener: View.OnClickListener) : RecyclerView.Adapter<ProfileViewHolder>() {

    val menus : ArrayList<Menu> = ArrayList()

    init {
        var icon = R.drawable.ic_account
        var title = context.resources.getString(R.string.title_account)
        var subTitle = context.resources.getString(R.string.sub_title_account)
        menus.add(Menu(R.id.account,icon,title, subTitle))

        icon = R.drawable.ic_settings
        title = context.resources.getString(R.string.title_settings)
        subTitle = context.resources.getString(R.string.sub_title_settings)
        menus.add(Menu(R.id.settings,icon,title, subTitle))

        icon = R.drawable.ic_help
        title = context.resources.getString(R.string.title_help)
        subTitle = context.resources.getString(R.string.sub_title_help)
        menus.add(Menu(R.id.help,icon,title, subTitle))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_notification,parent,false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        var menu : Menu = menus.get(position)
        holder.itemView.imageViewIcon.setImageResource(menu.icon)
        holder.itemView.textViewTitle.text = menu.title
        holder.itemView.textViewSubTitle.text = menu.subTitle
        holder.itemView.setOnClickListener(clickListener)
    }
}