package com.app.scrapapp.module_home.view.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.ui.notifications.NotificationsViewModel
import com.app.scrapapp.R
import com.bumptech.glide.Glide

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var notificationsViewModel: NotificationsViewModel
    var root : View? = null
    var rv : RecyclerView? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_notifications, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var appCompatImageView : AppCompatImageView? = root?.findViewById<AppCompatImageView>(R.id.img)
        Glide.with(requireActivity())
            .load(R.drawable.download)
            .circleCrop()
            .into(appCompatImageView as ImageView)


        rv = root?.findViewById<RecyclerView>(R.id.rv)
        rv?.layoutManager = LinearLayoutManager(this.requireActivity(),LinearLayoutManager.VERTICAL,false)
        rv?.adapter = ProfileAdapter(this.requireActivity(),this)
        rv?.addItemDecoration(ProfileItemDecoration(this.requireContext()))

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.layoutMain->{
                var position = rv?.getChildAdapterPosition(p0)
                var profileAdapter : ProfileAdapter? = rv?.adapter as ProfileAdapter?
                if (position != null) {
                    var menu : Menu? = profileAdapter?.menus?.get(position)
                    when(menu?.id){
                        /*R.id.settings->{
                            startActivity(Intent(requireActivity(),SettingsActivity2::class.java))
                        }*/
                    }

                }

            }
        }
    }
}