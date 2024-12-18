package com.example.kaizenapp.data.di

import com.example.kaizenapp.data.datasources.SportEventDataSource
import com.example.kaizenapp.data.datasources.SportEventListRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {
    @Binds
    abstract fun bindSportEventListRemoteDataSource(sportEventList: SportEventListRemoteDataSource): SportEventDataSource
}