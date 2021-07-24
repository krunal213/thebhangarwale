package com.app.scrapapp.module_profile.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.entity.ProfileDetails
import com.app.scrapapp.module_profile.view.viewholder.ProfileTypeOneViewHolder
import com.app.scrapapp.module_profile.view.viewholder.ProfileTypeTwoViewHolder
import kotlinx.android.synthetic.main.adapter_profile_type_one.view.*

const val TYPE_TWO = 2
const val TYPE_ONE = 1

class ProfileAdapter(var personProfileDetails: ArrayList<ProfileDetails>, var onClickListener: View.OnClickListener?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            TYPE_TWO->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_profile_type_two,parent,false)
                return ProfileTypeTwoViewHolder(view)
            }
            else->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_profile_type_one,parent,false)
                return ProfileTypeOneViewHolder(view)
            }
        }

    }

    override fun getItemCount(): Int {
        return personProfileDetails.size
    }


    override fun getItemViewType(position: Int): Int {
        when(position){
            2->{
                return TYPE_TWO
            }
            else->{
                return TYPE_ONE
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var profileDetails : ProfileDetails = personProfileDetails.get(position)
        when(holder){
            is ProfileTypeOneViewHolder->{
                holder.itemView.textViewLabel.text = profileDetails.title
                holder.itemView.textViewValue.text = profileDetails.text
                var image = profileDetails.image
                if(image!=null){
                    holder.itemView.imageView.setImageResource(image)
                }
                holder.itemView.setOnClickListener(onClickListener)
                holder.itemView.setTag(R.string.tag_profileDetails,profileDetails)
            }
            is ProfileTypeTwoViewHolder->{
                holder.itemView.textViewValue.text = profileDetails.text
                var image = profileDetails.image
                if(image!=null){
                    holder.itemView.imageView.setImageResource(image)
                }
                holder.itemView.setOnClickListener(onClickListener)
                holder.itemView.setTag(R.string.tag_profileDetails,profileDetails)
            }
        }


    }
}