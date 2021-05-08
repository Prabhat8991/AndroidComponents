package com.example.androidcomponentsreferenceapp.components.room.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(this.context).load(url).into(this)
}