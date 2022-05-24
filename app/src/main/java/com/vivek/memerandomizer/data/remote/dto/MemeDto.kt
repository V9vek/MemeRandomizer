package com.vivek.memerandomizer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MemeDto(

    @SerializedName("url")
    val imgUrl: String
)
