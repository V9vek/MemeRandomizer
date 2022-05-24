package com.vivek.memerandomizer.data.mapper

import com.vivek.memerandomizer.data.remote.dto.MemeDto
import com.vivek.memerandomizer.domain.model.Meme

fun MemeDto.toMeme(): Meme {
    return Meme(
        imgUrl = imgUrl
    )
}