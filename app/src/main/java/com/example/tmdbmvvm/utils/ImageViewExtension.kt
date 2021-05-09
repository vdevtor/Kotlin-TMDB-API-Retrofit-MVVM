package com.example.tmdbmvvm.utils


import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.tmdbmvvm.R

fun ImageView.load(url: String) {
    Glide.with(context)
            .load(url)
            .into(this)
}

fun ImageView.SetCheckedHeart() {
    this.setImageResource(R.drawable.ic_pressed_heart)
}

fun ImageView.SetUncheckedHeart() {
    this.setImageResource(R.drawable.ic_unpressed_heart)
}

