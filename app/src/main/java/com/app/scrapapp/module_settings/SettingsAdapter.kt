package com.app.scrapapp.module_settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import com.app.scrapapp.module_settings.SettingItem.*
import kotlinx.android.synthetic.main.adapter_header_settings.view.*
import kotlinx.android.synthetic.main.adapter_header_settings.view.textViewTitle
import kotlinx.android.synthetic.main.adapter_selection_pref_settings.view.*
import kotlinx.android.synthetic.main.adapter_switch_pref_settings.view.*

sealed class SettingItem {
    data class Header(val title: String) : SettingItem()
    data class SwitchPref(val title: String, val isEnabled: Boolean) : SettingItem()
    data class SelectionPref(
        val title: String,
        val subtitle: String,
        val menus: ArrayList<String>
    ) : SettingItem()
}

class SettingsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val settingsItems: ArrayList<SettingItem> = ArrayList<SettingItem>()

    init {
        settingsItems.add(Header("Notification"))
        settingsItems.add(SwitchPref("Enable", false))
        settingsItems.add(Header("Display"))
        val theme = ArrayList<String>().apply {
            add("Dark")
            add("Light")
        }
        settingsItems.add(SelectionPref("Theme", "Dark", theme))
        val language = ArrayList<String>().apply {
            add("English")
            add("Hindi")
            add("Marathi")
        }
        settingsItems.add(SelectionPref("Language", "English", language))
    }

    override fun onCreateViewHolder(p0: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return when (settingsItems[position]) {
            is Header -> {
                HeaderViewHolder(
                    LayoutInflater.from(p0.context)
                        .inflate(R.layout.adapter_header_settings, p0, false)
                )
            }
            is SwitchPref -> {
                SwitchPrefViewHolder(
                    LayoutInflater.from(p0.context)
                        .inflate(R.layout.adapter_switch_pref_settings, p0, false)
                )
            }
            is SelectionPref -> {
                SelectionPrefViewHolder(
                    LayoutInflater.from(p0.context)
                        .inflate(R.layout.adapter_selection_pref_settings, p0, false)
                )
            }
        }
    }

    override fun onBindViewHolder(viewHolder : RecyclerView.ViewHolder, p1: Int) {
        when(viewHolder){
            is HeaderViewHolder -> {
                val header : Header = settingsItems[p1] as Header
                header.apply {
                    viewHolder.itemView.textViewTitle.text = title
                }
            }
            is SwitchPrefViewHolder -> {
                val header : SwitchPref = settingsItems[p1] as SwitchPref
                header.apply {
                    viewHolder.itemView.textViewTextSwitchPref.text = title
                }
            }
            is SelectionPrefViewHolder -> {
                val header : SelectionPref = settingsItems[p1] as SelectionPref
                header.apply {
                    viewHolder.itemView.textViewTitle.text = title
                    viewHolder.itemView.textViewSubTitle.text = subtitle
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return settingsItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}