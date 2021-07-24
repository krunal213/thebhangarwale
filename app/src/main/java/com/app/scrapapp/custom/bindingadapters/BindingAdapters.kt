package com.app.scrapapp.custom.bindingadapters

import android.app.Activity
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import com.app.scrapapp.custom.adapters.TextWatcherAdapter
import com.app.scrapapp.custom.listeners.OnClickListener
import com.app.scrapapp.custom.widget.ProgressBarDialogFragment
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("android:onClick")
    fun View.setOnClickListener(clickListener: View.OnClickListener?) {
        clickListener?.also {
            setOnClickListener(OnClickListener(it))
        } ?: setOnClickListener(null)
    }

    @JvmStatic
    @BindingAdapter("errorMessage")
    fun TextInputLayout.setError(errorMessage : String?) {
        if(errorMessage!=null){
            this.error = errorMessage
        }
    }

    @JvmStatic
    @BindingAdapter("autoDismissError")
    fun EditText.setAutoDismissError(autoDismissError: Boolean) {
        when(autoDismissError){
            true->{
                this.addTextChangedListener(object : TextWatcherAdapter() {
                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        val view = this@setAutoDismissError.parent.parent
                        when(view){
                            is TextInputLayout->{
                                view.error = null
                            }
                            else->{
                                addTextChangedListener(null)
                            }
                        }
                    }
                })
            }
            else->{
                this.addTextChangedListener(null)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("showProgressBar","supportFragmentManager")
    fun View.progressBarVisible(show: Boolean,supportFragmentManager : FragmentManager) {
        var dialogFragment: ProgressBarDialogFragment? = null
        val availableFragment = supportFragmentManager.findFragmentByTag("progressBar")
        if(availableFragment!=null){
            dialogFragment = availableFragment as ProgressBarDialogFragment
        }else{
            dialogFragment = ProgressBarDialogFragment()
        }
        dialogFragment?.isCancelable = false
        when(show){
            true->{
                if(!dialogFragment.isVisible) {
                    dialogFragment.show(supportFragmentManager, "progressBar")
                }
            }
            else->{
                if(dialogFragment.isVisible) {
                    dialogFragment.dismiss()
                }
            }
        }
    }




}
