package com.example.androidcomponentsreferenceapp.components.mvvm

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("marsPropertyPrice")
fun TextView.setPrice(price: Double) {
    price.let {
        text = it.toString()
    }
}