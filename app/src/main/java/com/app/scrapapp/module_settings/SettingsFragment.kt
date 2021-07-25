package com.app.scrapapp.module_settings

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey)
        var listPreference : ListPreference? = findPreference<ListPreference>("theme")
        listPreference?.onPreferenceChangeListener = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                outRect.left = parent.resources.getDimension(R.dimen.recyclerview_edges).toInt()
                outRect.right = parent.resources.getDimension(R.dimen.recyclerview_edges).toInt()
            }
        })
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        when(preference){
            is ListPreference ->{
                return true
            }
        }
        return false
    }

    override fun setDivider(divider: Drawable?) {
        super.setDivider(ContextCompat.getDrawable(requireContext(), R.drawable.drawable_horizonatal_divider))
    }

    

}