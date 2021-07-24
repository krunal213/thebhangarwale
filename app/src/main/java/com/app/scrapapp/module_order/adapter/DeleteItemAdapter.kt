package com.app.scrapapp.module_order.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.module_order.viewholder.DeleteItemViewHolder
import kotlinx.android.synthetic.main.adapter_delete_item.view.*

class DeleteItemAdapter(val onClickListener: View.OnClickListener?) : RecyclerView.Adapter<DeleteItemViewHolder>() {

    val imageList : ArrayList<Uri?> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteItemViewHolder {
        val dm = parent.context.getResources().getDisplayMetrics()
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_delete_item, parent, false)
        view.layoutParams.width = dm.widthPixels/3
        view.layoutParams.height = dm.widthPixels/3
        return DeleteItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        val size =  imageList.size
        if(size<3){
            return size
        }else{
            return 3
        }
    }

    override fun onBindViewHolder(holder: DeleteItemViewHolder, position: Int) {
        val imageUri : Uri? = imageList.get(position)
        holder.itemView.imageViewForItems.setImageURI(imageUri)
        holder.itemView.imageViewForItems.setTag(R.string.tag_image_uri,imageUri)
        holder.itemView.imageViewForItems.setOnClickListener(onClickListener)
    }

    fun notifyAddDataSetThreeAndLessThanThreeChanged(imageList: ArrayList<Uri>?) {
        if(imageList!=null && imageList?.size<=3) {
            this.imageList?.addAll(imageList)
            notifyDataSetChanged()
        }
    }






}