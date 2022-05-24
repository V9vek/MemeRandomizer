package com.vivek.memerandomizer.presentation.meme

import android.graphics.Bitmap

sealed class MemeScreenEvent {

    data class ShowToast(val message: String) : MemeScreenEvent()

    data class ShareMeme(val bitmap: Bitmap) : MemeScreenEvent()

    // Add more events here
}
