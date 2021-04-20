package com.example.moviesapp.utils

import android.view.View
import android.widget.ImageView
import com.example.moviesapp.BuildConfig
import com.squareup.picasso.Picasso

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(url: String) {
    val fullUrl = "${BuildConfig.IMAGE_URL}$url"
    Picasso.get().load(fullUrl).into(this)
}