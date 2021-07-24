package com.app.scrapapp.module_address.view.adapter

import android.location.Address
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.module_address.view.viewholder.MyAddressViewHolder
import kotlinx.android.synthetic.main.adapter_my_address.view.*

class MyAddressAdapter(
    val myaddress: ArrayList<Address>,
    val longClickListener: View.OnLongClickListener?,
    val onClickListener: View.OnClickListener?) : RecyclerView.Adapter<MyAddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAddressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_my_address,parent,false)
        return MyAddressViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myaddress.size
    }

    override fun onBindViewHolder(holder: MyAddressViewHolder, position: Int) {
        val address =  myaddress.get(position)
        holder.itemView.textViewAddress.text = address.getAddressLine(0)
        holder.itemView.setOnLongClickListener(longClickListener)
        holder.itemView.imageViewMenu.setOnClickListener(onClickListener)
        holder.itemView.imageViewMenu.setTag(R.string.tag_address,address)
        /*TooltipCompat.setTooltipText(holder.itemView.textViewAddress,
            myaddress.get(position))*/
    }
}