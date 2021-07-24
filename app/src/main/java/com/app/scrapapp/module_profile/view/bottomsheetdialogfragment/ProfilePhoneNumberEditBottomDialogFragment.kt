package com.app.scrapapp.module_profile.view.bottomsheetdialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.scrapapp.R
import com.app.scrapapp.custom.constants.PROFILE_DETAILS
import com.app.scrapapp.entity.ProfileDetails
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_profile_edit_phone_number.view.*

class ProfilePhoneNumberEditBottomDialogFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(profileDetails: ProfileDetails?): ProfilePhoneNumberEditBottomDialogFragment {
            val frag = ProfilePhoneNumberEditBottomDialogFragment()
            val args = Bundle()
            args.putParcelable(PROFILE_DETAILS,profileDetails)
            frag.arguments = args
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogFragmentTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var profileDetails = arguments?.getParcelable<ProfileDetails>(PROFILE_DETAILS)
        val v = inflater.inflate(R.layout.dialog_profile_edit_phone_number, container, false)
        v.editTextPhoneNumber.setText(profileDetails?.text.toString().trim())
        return v
    }

}