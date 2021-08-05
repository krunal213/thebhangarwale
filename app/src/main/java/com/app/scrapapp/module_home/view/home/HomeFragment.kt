package com.app.scrapapp.module_home.view.home

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.myapplication.ui.home.HomeViewModel
import com.app.scrapapp.R
import com.app.scrapapp.module_address.view.activity.CreateCustomAddressActivity
import com.app.scrapapp.module_address.view.activity.MyAddressActivity
import com.app.scrapapp.module_checkout.ChooseAddressActivity
import com.app.scrapapp.module_home.view.SmoothRefreshLayoutHeader
import com.app.scrapapp.module_order.AddItemActivity
import com.app.scrapapp.module_order.AddItemActivityV2
import com.app.scrapapp.module_order.UpdateItemActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.mRefreshLayout
import kotlinx.android.synthetic.main.fragment_home.recyclerView
import kotlinx.android.synthetic.main.fragment_home.toolbar
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import me.dkzwm.widget.srl.indicator.IIndicator


class HomeFragment : Fragment(), View.OnLongClickListener, Toolbar.OnMenuItemClickListener,
    View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private val mHandler = Handler()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //container_Shimmer.startShimmer()

        //Handler().postDelayed(Runnable { init() }, 5000)


        println("hi test")


        init()

        //recyclerView.addItemDecoration(DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL))
    }


    private fun init() {

        container_Shimmer.stopShimmer()
        container_Shimmer.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        floatingActionButton.visibility = View.VISIBLE
        layoutProceed.visibility = View.VISIBLE

        toolbar.setNavigationIcon(R.drawable.ic_close)
        toolbar.setTitle("1" + "\t\t" + "Selected")


        toolbar.setNavigationOnClickListener { view ->

            app_bar.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window: Window = requireActivity().window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                var color = requireActivity().resources.getColor(R.color.color_status_bar)
                window.statusBarColor = color
            }
        }
        toolbar.inflateMenu(R.menu.menu_item)
        toolbar.setOnMenuItemClickListener(this)
        floatingActionButton.setOnClickListener(this)

        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = HomeAdapter(this.requireActivity() as AppCompatActivity, this, this)
        recyclerView.addItemDecoration(HomeItemDecoration(requireContext()))

        val header = SmoothRefreshLayoutHeader<IIndicator>(this.requireActivity())
        mRefreshLayout.setHeaderView(header)

        mRefreshLayout.setOnRefreshListener(
            object : RefreshingListenerAdapter() {
                override fun onRefreshing() {
                    mHandler.postDelayed(
                        Runnable {
                            mRefreshLayout.refreshComplete()
                        },
                        2000
                    )
                }

                override fun onLoadingMore() {
                    mHandler.postDelayed(
                        Runnable {
                            mRefreshLayout.refreshComplete()
                        },
                        2000
                    )
                }
            })
        mRefreshLayout.autoRefresh(false)
        layoutProceed.setOnClickListener(this)


    }


    override fun onLongClick(p0: View?): Boolean {
        when (p0?.id) {
            R.id.item -> {
                if (app_bar.visibility != View.VISIBLE){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        val window: Window = requireActivity().window
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                        var color = requireActivity().resources.getColor(R.color.colorPrimaryHome)
                        window.statusBarColor = color
                    }
                    app_bar.visibility = View.VISIBLE
                }

            }
        }
        return true
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
             R.id.navigation_delete->{
                 MaterialAlertDialogBuilder(requireContext())
                     .setCancelable(false)
                     .setMessage("Are you sure you want to delete items?")
                     .setNegativeButton("Cancel") { dialog, which ->
                     }
                     .setPositiveButton("OK") { dialog, which ->
                     }
                     .show()
             }
        }
        return true
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.item -> {
                val intent: Intent =
                    Intent(this.requireActivity(), UpdateItemActivity::class.java)
                /*val options = ActivityOptions.makeSceneTransitionAnimation(
                    requireActivity(),
                    p0,
                    "shared_element_container" // The transition name to be matched in Activity B.
                )*/
                startActivity(intent)
            }
            R.id.floatingActionButton ->{
                val intent: Intent =
                    Intent(this.requireActivity(), AddItemActivityV2::class.java)
                /*val options = ActivityOptions.makeSceneTransitionAnimation(
                    requireActivity(),
                    p0,
                    "shared_element_container" // The transition name to be matched in Activity B.
                )*/
                startActivity(intent)
            }
            R.id.layoutProceed->{
                val intent: Intent =
                    Intent(this.requireActivity(), ChooseAddressActivity::class.java)
                /*val options = ActivityOptions.makeSceneTransitionAnimation(
                    requireActivity(),
                    p0,
                    "shared_element_container" // The transition name to be matched in Activity B.
                )*/
                startActivity(intent)
            }
        }
    }

}