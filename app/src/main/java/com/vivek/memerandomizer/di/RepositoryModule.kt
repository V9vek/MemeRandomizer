package com.vivek.memerandomizer.di

import com.vivek.memerandomizer.data.repository.MemeRepositoryImpl
import com.vivek.memerandomizer.domain.repository.MemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindMemeRepository(
        memeRepositoryImpl: MemeRepositoryImpl
    ): MemeRepository
}





















