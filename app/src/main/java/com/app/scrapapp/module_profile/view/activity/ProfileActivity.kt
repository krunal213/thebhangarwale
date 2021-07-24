package com.app.scrapapp.module_profile.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.entity.ProfileDetails
import com.app.scrapapp.module_address.view.activity.AddAddressActivity
import com.app.scrapapp.module_address.view.activity.MyAddressActivity
import com.app.scrapapp.module_profile.view.adapters.ProfileAdapter
import com.app.scrapapp.module_profile.view.bottomsheetdialogfragment.ProfileNameEditBottomDialogFragment
import com.app.scrapapp.module_profile.view.bottomsheetdialogfragment.ProfilePhoneNumberEditBottomDialogFragment
import com.app.scrapapp.module_profile.view.itemdecoration.ProfileItemDecoration
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    val personProfileDetails: ArrayList<ProfileDetails> = ArrayList<ProfileDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.title_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)


        personProfileDetails.add(
            ProfileDetails(
                resources.getString(R.string.title_name),
                "Krunal Kathikar",
                R.drawable.ic_profile_name
            )
        )
        personProfileDetails.add(
            ProfileDetails(
                resources.getString(R.string.title_phone),
                "+91 8806616913",
                R.drawable.ic_profile_phone_number
            )
        )

        personProfileDetails.add(
            ProfileDetails(
                null,
                resources.getString(R.string.title_my_address),
                R.drawable.ic_address
            )
        )

        recyclerViewProfile.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerViewProfile.addItemDecoration(ProfileItemDecoration(this))
        recyclerViewProfile.adapter = ProfileAdapter(personProfileDetails, this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.constraintLayoutProfile -> {
                val profileDetails: ProfileDetails? = view.getTag(R.string.tag_profileDetails) as ProfileDetails?
                editProfileDetails(profileDetails)

            }

            R.id.constraintLayoutProfileTypeTwo->{
                val intentAddAddress = Intent(this,MyAddressActivity::class.java)
                startActivity(intentAddAddress)
            }
        }
    }

    fun editProfileDetails(profileDetails: ProfileDetails?) {
        title = profileDetails?.title
        when (title) {
            resources.getString(R.string.title_name) -> {
                val profileNameEditBottomDialogFragment = ProfileNameEditBottomDialogFragment.newInstance(
                    profileDetails
                )
                profileNameEditBottomDialogFragment.show(
                    supportFragmentManager,
                    profileNameEditBottomDialogFragment.javaClass.simpleName
                )
            }
            resources.getString(R.string.title_phone) -> {
                val profilePhoneNumberEditBottomDialogFragment = ProfilePhoneNumberEditBottomDialogFragment.newInstance(
                    profileDetails
                )
                profilePhoneNumberEditBottomDialogFragment.show(
                    supportFragmentManager,
                    profilePhoneNumberEditBottomDialogFragment.javaClass.simpleName
                )
            }
        }
    }

}

