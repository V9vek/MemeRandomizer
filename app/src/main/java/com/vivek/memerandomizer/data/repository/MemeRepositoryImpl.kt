package com.vivek.memerandomizer.data.repository

import com.vivek.memerandomizer.data.mapper.toMeme
import com.vivek.memerandomizer.data.remote.MemeApi
import com.vivek.memerandomizer.domain.model.Meme
import com.vivek.memerandomizer.domain.repository.MemeRepository
import com.vivek.memerandomizer.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemeRepositoryImpl @Inject constructor(
    private val api: MemeApi
) : MemeRepository {

    override suspend fun getMeme(
        subreddit: String
    ): Flow<Resource<Meme>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            try {
                val memeDto = api.getMeme(subreddit = subreddit)
                emit(Resource.Success(data = memeDto.toMeme()))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message ?: "Unknown Error!"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message ?: "Unknown Error!"))
            }

            emit(Resource.Loading(isLoading = false))
        }
    }
}






























