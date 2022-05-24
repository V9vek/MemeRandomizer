package com.vivek.memerandomizer.presentation.meme

import com.vivek.memerandomizer.domain.model.Meme

data class MemeScreenState(
    val meme: Meme = Meme(imgUrl = ""),
    val isLoading: Boolean = false
)
