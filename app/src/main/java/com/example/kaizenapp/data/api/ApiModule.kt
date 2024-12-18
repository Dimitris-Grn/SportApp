package com.example.kaizenapp.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideMarvelApiService(retrofit: Retrofit): SportEventApi =
        retrofit.create(SportEventApi::class.java)
}