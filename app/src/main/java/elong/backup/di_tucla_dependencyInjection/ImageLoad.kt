package elong.backup.di_tucla_dependencyInjection

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoad {
    fun load(url:String, img:ImageView) = Picasso.get().load(url).into(img)
}