package com.ibrahimethem.todoremember.util.extensions

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load

fun ImageView.downloadImage(imageUrl : String?){
    val placeHolder = generatePlaceHolder(this.context)
    this.load(data = imageUrl){
        crossfade(true)
        crossfade(500)
        placeholder(placeHolder)
        listener(
            onSuccess = { _,_ ->

            },
            onError = {_,_ ->

            }
        )
    }
}

private fun generatePlaceHolder(context : Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 12f
        centerRadius = 40f
        start()
    }
}