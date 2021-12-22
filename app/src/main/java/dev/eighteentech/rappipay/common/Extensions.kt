package dev.eighteentech.rappipay.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dev.eighteentech.rappipay.R
import dev.eighteentech.rappipay.common.Constants.IMAGE_BASE_URL

fun <T> List<T>.mix(other: List<T>): List<T> {
    val first = iterator()
    val second = other.iterator()
    val list = ArrayList<T>(minOf(this.size, other.size))
    while (first.hasNext() && second.hasNext()) {
        list.add(first.next())
        list.add(second.next())
    }
    return list
}

fun ImageView.setFromUrl(url:String){
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.broken)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.setTMDBUrl(url:String?){
    var u = url?:""
    Glide.with(this)
        .load(IMAGE_BASE_URL + u)
        .placeholder(R.drawable.broken)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}