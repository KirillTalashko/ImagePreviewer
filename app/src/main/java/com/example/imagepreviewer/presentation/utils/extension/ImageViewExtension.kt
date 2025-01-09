package com.example.imagepreviewer.presentation.utils.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.imagepreviewer.R


fun ImageView.loadPhoto(
    url: String?,
    placeholder: Int = R.drawable.ic_loading,
    error: Int = R.drawable.no_image
) {
    Glide.with(this.rootView)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}