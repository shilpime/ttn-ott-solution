package com.ottsolution.demo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ottsolution.demo.R


/**
 * Created by Srikant Karnani on 30/7/19.
 */
class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("glideSrc")
        fun ImageView.setGlideSrc(url: String?) {
            Glide.with(this.context).load(url).placeholder(R.drawable.splash_bg).into(this)
        }
    }
}