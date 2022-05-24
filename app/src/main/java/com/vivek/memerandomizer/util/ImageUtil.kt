package com.vivek.memerandomizer.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build.VERSION.SDK_INT
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale

fun ImageView.loadImage(imgUrl: String) {
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    load(data = imgUrl, imageLoader = imageLoader) {
        scale(Scale.FIT)
        crossfade(true)
        crossfade(20)
    }
}

suspend fun getBitmapFromUrl(
    url: String,
    context: Context
): Bitmap {
    val loading = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(url)
        .build()

    val result = (loading.execute(request) as SuccessResult).drawable
    return (result as BitmapDrawable).bitmap
}



















