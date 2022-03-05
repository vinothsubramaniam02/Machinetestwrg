/*
 * *
 *  * Created by Nethaji on 27/6/20 1:18 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 27/6/20 12:32 PM
 *
 */
package com.hr.data.network.di
import android.app.Activity

import android.content.Context

import android.graphics.Bitmap

import android.net.ConnectivityManager

import android.widget.ImageView

import androidx.core.content.ContextCompat

import com.bumptech.glide.Glide

import com.kaopiz.kprogresshud.KProgressHUD






fun ImageView.loadCircularImage(image: Any) {
    Glide.with(context)
        .load(
            when (image) {
                is Int -> ContextCompat.getDrawable(context, image)
                is Bitmap -> image
                else -> image
            }
        )
        .circleCrop()
        .into(this)
}


fun ImageView.loadImage(image: Any) {
    Glide.with(context)
        .load(
            when (image) {
                is Int -> ContextCompat.getDrawable(context, image)
                is Bitmap -> image
                else -> image
            }
        )
              .into(this)
}





typealias result = (Boolean) -> Unit



fun isConnected(activity: Activity): Boolean {
    val connectivityManager =
        activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null
}



private  var progressHuD : KProgressHUD? = null


fun Context.progressBarShow(context: Context){
    progressHuD= KProgressHUD.create(context)
        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        .setLabel("Please wait")
        //.setDetailsLabel("Downloading data")
        .setCancellable(true)
        .setAnimationSpeed(2)
        .setDimAmount(0.5f)
        .setBackgroundColor(57000000)
        .show()
}

fun Context.progressBarDismiss(it: Context) {
    progressHuD?.dismiss()
}