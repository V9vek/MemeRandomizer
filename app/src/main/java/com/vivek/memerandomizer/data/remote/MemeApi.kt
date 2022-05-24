package com.vivek.memerandomizer.data.remote

import com.vivek.memerandomizer.data.remote.dto.MemeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MemeApi {

    @GET("gimme/{subreddit}")
    suspend fun getMeme(
        @Path("subreddit")
        subreddit: String = ""
    ): MemeDto

    companion object {
        const val BASE_URL = "https://meme-api.herokuapp.com"
    }
}























