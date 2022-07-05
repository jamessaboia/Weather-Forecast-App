package com.jamessaboia.weatherforecast.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.jamessaboia.weatherforecast.R
import com.jamessaboia.weatherforecast.utils.internal.glide.GlideApp

class BidingUtils {

    companion object {

        @JvmStatic
        @BindingAdapter("bind:glideLoad")
        fun loadImageView(image: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {

                GlideApp.with(image)
                    .load("https:${url}")
                    .error(R.drawable.partly_cloudy)
                    .into(image)
            }
        }

        @JvmStatic
        fun convertFloatToInt(value: Float): Int {
            return value.toInt()
        }

    }
}