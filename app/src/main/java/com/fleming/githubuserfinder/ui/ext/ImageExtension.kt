package com.fleming.githubuserfinder.ui.ext

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(imageUrl: String) {
    Glide.with(context).load(imageUrl).into(this)
}