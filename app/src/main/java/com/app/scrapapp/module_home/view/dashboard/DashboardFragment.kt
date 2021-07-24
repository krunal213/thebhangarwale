package com.app.scrapapp.module_home.view.dashboard

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.myapplication.ui.dashboard.DashboardViewModel
import com.app.scrapapp.R
import com.app.scrapapp.module_home.view.SmoothRefreshLayoutHeader
import kotlinx.android.synthetic.main.fragment_dashboard.*
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import me.dkzwm.widget.srl.indicator.IIndicator


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private val mHandler = Handler()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Handler().postDelayed(Runnable { init() }, 5000)
        init()

    }

    fun init() {

        //container_Shimmer.stopShimmer()
        //container_Shimmer.visibility = View.GONE

        toolbar.inflateMenu(R.menu.menu_home_page)
        //toolbar.setNavigationIcon(R.drawable.ic_info)

        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = DashboardAdapter(requireActivity() as AppCompatActivity)
        recyclerView.addItemDecoration(DashBoardItemDecoration(requireActivity()))

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


    }


}