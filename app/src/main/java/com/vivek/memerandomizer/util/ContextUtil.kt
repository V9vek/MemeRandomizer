package com.vivek.memerandomizer.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.StrictMode
import java.io.File
import java.io.FileOutputStream

/**
 * For more info: https://github.com/codepath/android_guides/wiki/Sharing-Content-with-Intents
 */
fun Context.shareMeme(bitmap: Bitmap) {
    val builder = StrictMode.VmPolicy.Builder()
    StrictMode.setVmPolicy(builder.build())
    val file = File("$externalCacheDir/Meme.png")

    val shareIntent = Intent(Intent.ACTION_SEND)

    try {
        val outStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
        outStream.apply {
            flush()
            close()
        }
        shareIntent.apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    startActivity(Intent.createChooser(shareIntent, "Share meme"))
}



























