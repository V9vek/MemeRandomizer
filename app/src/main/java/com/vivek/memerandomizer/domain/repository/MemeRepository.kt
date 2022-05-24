package com.vivek.memerandomizer.domain.repository

import com.vivek.memerandomizer.domain.model.Meme
import com.vivek.memerandomizer.util.Resource
import kotlinx.coroutines.flow.Flow

interface MemeRepository {

    suspend fun getMeme(
        subreddit: String
    ): Flow<Resource<Meme>>
}






















