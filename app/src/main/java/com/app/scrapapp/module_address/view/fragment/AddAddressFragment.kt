package com.app.scrapapp.module_address.view.fragment

import android.app.Activity
import android.content.Intent
import android.location.Address
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.app.scrapapp.R
import com.app.scrapapp.custom.constants.RequestCode.REQUEST_MY_LOCATION
import com.app.scrapapp.custom.constants.SUGGESTED_SELECTED_ADDRESS
import com.app.scrapapp.custom.listeners.IActivityListener
import com.app.scrapapp.custom.listeners.IFragmentListener
import com.app.scrapapp.module_address.view.activity.AddAddressActivity
import com.app.scrapapp.module_address.view.activity.CreateCustomAddressActivity
import com.app.scrapapp.test.ExplodeTransitionTestActvity
import kotlinx.android.synthetic.main.fragment_add_address.*

class AddAddressFragment : Fragment(), IActivityListener, Toolbar.OnMenuItemClickListener {

    private var iFragmentListener : IFragmentListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_address, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddAddressFragment().apply {}
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        iFragmentListener = this.activity as AddAddressActivity

        toolbar.inflateMenu(R.menu.menu_create_address)
        toolbar.title = "Save Address"
        toolbar.setOnMenuItemClickListener(this)

        textInputEditTextLocation.setOnClickListener {
            val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity as AppCompatActivity)
            (this@AddAddressFragment.activity as AddAddressActivity).startActivityFromFragment(
                this,
                Intent(this.activity, ExplodeTransitionTestActvity::class.java),
                REQUEST_MY_LOCATION,
                options.toBundle()
            )
        }


    }

    override fun <T> notify(t: T) {
        when(t){
            is String->{
                println("Address : "+t)
                textInputEditTextLocation.setText(t)
            }
        }

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.navigation_create_address->{
                val intentCreateAddress = Intent(this.activity,CreateCustomAddressActivity::class.java)
                startActivity(intentCreateAddress)
            }
        }
        return true
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            Activity.RESULT_OK->{
                when(requestCode){
                    REQUEST_MY_LOCATION->{
                        val suggestedSelectedAddress = data?.getParcelableExtra<Address>(SUGGESTED_SELECTED_ADDRESS)
                        //println("Address : "+)
                        textInputEditTextLocation.setText(suggestedSelectedAddress?.getAddressLine(0))
                        iFragmentListener?.notify(suggestedSelectedAddress)
                    }
                }
            }
        }
    }
}

