package com.app.scrapapp.module_home.view.dashboard

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.bumptech.glide.Glide
import com.hrskrs.instadotlib.InstaDotView
//import kotlinx.android.synthetic.main.adapter_dashboard_single_image.view.imageProfile

sealed class Post(open var dataType : Int){
    data class SingleImage(val imageUrl : String?,override var dataType: Int = 0) : Post(dataType)
    data class MultipleImage(val images : ArrayList<String>?,override var dataType: Int = 1) : Post(dataType)
    data class Video(val videoUrl : String?,override var dataType: Int = 2) : Post(dataType)
}

class DashboardAdapter(val appCompatActivity : AppCompatActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val posts : ArrayList<Post> = ArrayList()

    init {
        val images : ArrayList<String>? = ArrayList<String>()
        images?.add("https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg")
        images?.add("https://images.unsplash.com/photo-1539946309076-4daf2ea73899?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80")
        images?.add("https://wallpapercave.com/wp/wp2634222.jpg")
        posts.add(Post.MultipleImage(images))

       /* val imageSingle : String? = "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg"
        posts.add(Post.SingleImage(imageSingle))

        val imageSingleTwo : String? = "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg"
        posts.add(Post.SingleImage(imageSingleTwo))

        val imageSingleThree : String? = "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg"
        posts.add(Post.SingleImage(imageSingleThree))
*/
        val imagesFour : ArrayList<String>? = ArrayList<String>()
        imagesFour?.add("https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg")
        imagesFour?.add("https://images.unsplash.com/photo-1539946309076-4daf2ea73899?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80")
        imagesFour?.add("https://wallpapercave.com/wp/wp2634222.jpg")
        posts.add(Post.MultipleImage(imagesFour))



        val imagesFive : ArrayList<String>? = ArrayList<String>()
        imagesFive?.add("https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg")
        imagesFive?.add("https://images.unsplash.com/photo-1539946309076-4daf2ea73899?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80")
        imagesFive?.add("https://wallpapercave.com/wp/wp2634222.jpg")
        posts.add(Post.MultipleImage(imagesFive))


        val imagesSix : ArrayList<String>? = ArrayList<String>()
        imagesSix?.add("https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg")
        imagesSix?.add("https://images.unsplash.com/photo-1539946309076-4daf2ea73899?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80")
        imagesSix?.add("https://wallpapercave.com/wp/wp2634222.jpg")
        posts.add(Post.MultipleImage(imagesSix))


        val imagesSeven : ArrayList<String>? = ArrayList<String>()
        imagesSeven?.add("https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg")
        imagesSeven?.add("https://images.unsplash.com/photo-1539946309076-4daf2ea73899?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80")
        imagesSeven?.add("https://wallpapercave.com/wp/wp2634222.jpg")
        posts.add(Post.MultipleImage(imagesSeven))


        val imagesEight : ArrayList<String>? = ArrayList<String>()
        imagesEight?.add("https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg")
        imagesEight?.add("https://images.unsplash.com/photo-1539946309076-4daf2ea73899?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80")
        imagesEight?.add("https://wallpapercave.com/wp/wp2634222.jpg")
        posts.add(Post.MultipleImage(imagesEight))

        /*val imageSingleFive : String? = "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg"
        posts.add(Post.SingleImage(imageSingleFive))

        val imageSingleSix : String? = "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg"
        posts.add(Post.SingleImage(imageSingleSix))

        val imageSingleSeven : String? = "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg"
        posts.add(Post.SingleImage(imageSingleSeven))*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder : RecyclerView.ViewHolder? = null
       /* when(viewType){
            0->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_dashboard_single_image,parent,false)
                viewHolder = DashboardSingleImageViewHolder(view)
            }
            1->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_dashboard_v3,parent,false)
                viewHolder = DashboardMultipleImageViewHolder(view)
            }
        }*/
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_dashboard_single_image,parent,false)
        viewHolder = DashboardSingleImageViewHolder(view)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        println("Holder : "+holder)
        /*when(holder){
            is DashboardSingleImageViewHolder->{
                Glide.with(appCompatActivity)
                    .load(R.drawable.ic_bhangarwale_facebook)
                    .circleCrop()
                    .into(holder.itemView.imageProfile)
            }
            is DashboardMultipleImageViewHolder->{
                var recyclerviewImages : RecyclerView = holder.itemView.recyclerviewImages
                if(recyclerviewImages.adapter==null){
                    holder.itemView.toolbar.inflateMenu(R.menu.menu_layout_dashboard_v3)
                    recyclerviewImages.adapter = MultipleImageAdapter()
                    recyclerviewImages.layoutManager = LinearLayoutManager(appCompatActivity,LinearLayoutManager.HORIZONTAL,false)
                    var scrollingPagerIndicator : InstaDotView = holder.itemView.indicator
                    scrollingPagerIndicator.noOfPages = 20
                    var pagerSnapHelper : PagerSnapHelper = PagerSnapHelper()
                    pagerSnapHelper.attachToRecyclerView(recyclerviewImages)
                }
            }
        }*/
    }
}