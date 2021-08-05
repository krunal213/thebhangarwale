package com.app.scrapapp.module_address.view.activity

import android.content.Intent
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.scrapapp.R
import com.app.scrapapp.custom.constants.EDIT_AVAILABLE_ADDRESS
import com.app.scrapapp.module_address.view.adapter.MyAddressAdapter
import com.app.scrapapp.module_address.view.decoration.MyAddressItemDecoration
import com.app.scrapapp.module_home.view.SmoothRefreshLayoutHeader
import com.app.scrapapp.module_home.view.home.HomeItemDecoration
import kotlinx.android.synthetic.main.activity_my_address.*
import kotlinx.android.synthetic.main.activity_my_address.mRefreshLayout
import kotlinx.android.synthetic.main.fragment_home.*
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import me.dkzwm.widget.srl.indicator.IIndicator
import java.util.*
import kotlin.collections.ArrayList

class MyAddressActivity : AppCompatActivity(), View.OnLongClickListener, View.OnClickListener {

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_address)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_my_address)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        //imageViewAdd.setOnClickListener(this)

        //18.98307494631155,72.83906176686287
        val myaddress = ArrayList<Address>()
        val address = Address(Locale.ENGLISH)
        address.setAddressLine(0,"4D, Harish Arjun Palay Marg, New Patra Chawl, Dhaku Prabhuchi Wadi, Byculla East, Byculla, Mumbai, Maharashtra 400033, India")
        address.latitude = 18.98307494631155
        address.longitude = 72.83906176686287

        myaddress.add(address)
        myaddress.add(address)
        myaddress.add(address)
        myaddress.add(address)

        val myAddressAdapter = MyAddressAdapter(myaddress,this,this)
        recyclerviewAddress.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerviewAddress.adapter = myAddressAdapter
        recyclerviewAddress.addItemDecoration(MyAddressItemDecoration(this))

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu_my_address,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_address->{
                val intentAddAddress = Intent(this,AddAddressActivity::class.java)
                startActivity(intentAddAddress)
            }
        }
        return true
    }

    override fun onLongClick(v: View?): Boolean {
        when(v?.id){
            R.id.constraintLayoutAddress->{
                startActionMode(contextualMenuCallBack)
            }
        }
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imageViewMenu->{
                val popup = PopupMenu(this, v)
                val inflater: MenuInflater = popup.menuInflater
                inflater.inflate(R.menu.pop_up_menu_my_address, popup.menu)
                popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
                    override fun onMenuItemClick(item: MenuItem?): Boolean {
                        when(item?.itemId){
                            R.id.action_google_map->{
                                val address : Address? = v.getTag(R.string.tag_address) as Address?
                                val intentAddress = Intent(this@MyAddressActivity,AddAddressActivity::class.java)
                                intentAddress.putExtra(EDIT_AVAILABLE_ADDRESS,address)
                                this@MyAddressActivity.startActivity(intentAddress)
                            }

                            R.id.action_edit->{
                                val address : Address? = v.getTag(R.string.tag_address) as Address?
                                val intentUpdateAddress = Intent(this@MyAddressActivity,UpdateAddressActivity::class.java)
                                intentUpdateAddress.putExtra(EDIT_AVAILABLE_ADDRESS,address)
                                this@MyAddressActivity.startActivity(intentUpdateAddress)
                            }
                        }
                        return true
                    }

                })
                popup.show()
            }
        }

    }


    private val contextualMenuCallBack = object : ActionMode.Callback {
        // Called when the action mode is created; startActionMode() was called
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.context_menu_my_address, menu)
            return true
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            /*return when (item.itemId) {
                R.id.menu_share -> {
                    shareCurrentItem()
                    mode.finish() // Action picked, so close the CAB
                    true
                }
                else -> false
            }*/

            return false
        }

        // Called when the user exits the action mode
        override fun onDestroyActionMode(mode: ActionMode) {
            //mode = null
        }
    }




}