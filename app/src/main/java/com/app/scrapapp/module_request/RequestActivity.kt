package com.app.scrapapp.module_request

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.module_account.AccountAdapter
import com.app.scrapapp.module_home.view.SmoothRefreshLayoutHeader
import com.app.scrapapp.module_profile.view.itemdecoration.AccountItemDecoration
import kotlinx.android.synthetic.main.activity_my_address.*
import kotlinx.android.synthetic.main.activity_request.*
import kotlinx.android.synthetic.main.activity_request.mRefreshLayout
import kotlinx.android.synthetic.main.activity_request.toolbar
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import me.dkzwm.widget.srl.indicator.IIndicator

class RequestActivity : AppCompatActivity(), View.OnClickListener {

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_requests)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val rv : RecyclerView = findViewById<RecyclerView>(R.id.recyclerviewItems)
        rv?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        rv?.adapter = RequestAdapter(this,this)
        rv?.addItemDecoration(RequestItemDecoration(this))

        val header = SmoothRefreshLayoutHeader<IIndicator>(this)
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

    override fun onClick(v: View?) {

    }

}