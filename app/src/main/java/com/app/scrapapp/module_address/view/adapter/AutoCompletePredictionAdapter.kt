package com.app.scrapapp.module_address.view.adapter

import android.location.Address
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.module_address.view.viewholder.AutoCompletePredictionViewHolder
import kotlinx.android.synthetic.main.adapter_auto_complete_prediction.view.*
import kotlin.collections.ArrayList

class AutoCompletePredictionAdapter(val onClickListener: View.OnClickListener?) : RecyclerView.Adapter<AutoCompletePredictionViewHolder>() {

    val suggestedAddress : ArrayList<Address> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoCompletePredictionViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_auto_complete_prediction,parent,false)
        return AutoCompletePredictionViewHolder(
            view
        )
    }
    
    override fun getItemCount(): Int {
        return suggestedAddress.size
    }

    override fun onBindViewHolder(holder: AutoCompletePredictionViewHolder, position: Int) {
        val suggestedAddress : Address? = suggestedAddress.get(position)
        holder.itemView.textViewTitle.text = suggestedAddress?.getAddressLine(0)
        holder.itemView.setTag(R.string.tag_suggestion,suggestedAddress)
        holder.itemView.setOnClickListener(onClickListener)
    }

    fun addAll(suggestedAddress: ArrayList<Address>?){
        this.suggestedAddress.clear()
        if (suggestedAddress != null) {
            this.suggestedAddress?.addAll(suggestedAddress)
        }
        notifyDataSetChanged()
    }


}