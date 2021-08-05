package com.app.scrapapp.module_checkout

import android.location.Address
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import kotlinx.android.synthetic.main.adapter_choose_address.view.*

class ChooseAddressAdapter(
    val myaddress: ArrayList<Address>,
    val longClickListener: View.OnLongClickListener?,
    val onClickListener: View.OnClickListener?) : RecyclerView.Adapter<ChooseAddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseAddressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_choose_address,parent,false)
        return ChooseAddressViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myaddress.size
    }

    override fun onBindViewHolder(holder: ChooseAddressViewHolder, position: Int) {
        val address =  myaddress.get(position)
        holder.itemView.textViewAddress.text = address.getAddressLine(0)
        holder.itemView.setOnLongClickListener(longClickListener)
        holder.itemView.imageViewMenu.setOnClickListener(onClickListener)
        holder.itemView.imageViewMenu.setTag(R.string.tag_address,address)
        /*TooltipCompat.setTooltipText(holder.itemView.textViewAddress,
            myaddress.get(position))*/
    }
}