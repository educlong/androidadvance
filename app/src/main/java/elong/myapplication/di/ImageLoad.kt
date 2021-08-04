package elong.myapplication.di

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoad {
    fun load(url:String, img:ImageView) = Picasso.get().load(url).into(img)
}