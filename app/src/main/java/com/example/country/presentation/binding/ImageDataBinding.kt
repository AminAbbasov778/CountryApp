package com.example.ecommerceapp.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.country.R
import com.google.android.material.imageview.ShapeableImageView

import com.squareup.picasso.Picasso

object ImageDataBinding {

    @BindingAdapter("load_url")
    @JvmStatic
    fun loadUrl(imageView: ShapeableImageView,url :String){
        Picasso.get().load(url).error(R.color.white).placeholder(R.color.white).into(imageView)

    }
}