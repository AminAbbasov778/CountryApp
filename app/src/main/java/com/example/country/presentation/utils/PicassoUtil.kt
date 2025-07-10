package com.example.country.presentation.utils

import android.widget.ImageView
import com.example.country.R
import com.squareup.picasso.Picasso

object PicassoUtil {
    fun ImageView.loadUrl(url :String?){
        Picasso.get().load(url).error(R.color.white).placeholder(R.color.white).into(this)
    }
}