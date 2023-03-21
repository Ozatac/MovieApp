package com.tunahanozatac.movieapp.util.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tunahanozatac.movieapp.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).centerCrop().placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_error_48).transform(CenterCrop(), RoundedCorners(25)).into(this)
}