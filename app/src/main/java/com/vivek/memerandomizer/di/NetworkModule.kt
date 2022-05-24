package com.vivek.memerandomizer.di

import com.google.gson.GsonBuilder
import com.vivek.memerandomizer.data.remote.MemeApi
import com.vivek.memerandomizer.data.remote.MemeApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMemeApi(): MemeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MemeApi::class.java)
    }
}





























