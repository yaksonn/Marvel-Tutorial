package com.yaksonn.marveltutorial.common.ext

import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.yaksonn.marveltutorial.common.R

fun ImageView.load(
    url: String?,
    circularProgressDrawable: CircularProgressDrawable
) {
    url?.let {
        Glide.with(this).load(it)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_image).into(this)
    }
}


fun View.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}