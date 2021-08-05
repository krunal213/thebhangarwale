package com.app.scrapapp.module_home.view.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.ui.notifications.NotificationsViewModel
import com.app.scrapapp.R
import com.app.scrapapp.module_account.AccountActivity
import com.app.scrapapp.module_help.HelpActivity
import com.app.scrapapp.module_profile.view.activity.ProfileActivity
import com.app.scrapapp.module_settings.SettingsActivity
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

        root?.findViewById<ConstraintLayout>(R.id.item_profile)?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.layoutMain->{
                var position = rv?.getChildAdapterPosition(p0)
                var profileAdapter : ProfileAdapter? = rv?.adapter as ProfileAdapter?
                if (position != null) {
                    var menu : Menu? = profileAdapter?.menus?.get(position)
                    when(menu?.id){
                        R.id.account->{
                            startActivity(Intent(requireActivity(),AccountActivity::class.java))
                        }
                        R.id.settings->{
                            startActivity(Intent(requireActivity(), SettingsActivity::class.java))
                        }
                        R.id.help->{
                            startActivity(Intent(requireActivity(), HelpActivity::class.java))
                        }
                    }

                }

            }
            R.id.item_profile->{
                startActivity(Intent(requireActivity(), ProfileActivity::class.java))
            }
        }
    }
}