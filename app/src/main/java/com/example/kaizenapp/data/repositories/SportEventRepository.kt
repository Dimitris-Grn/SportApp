package com.example.kaizenapp.data.repositories

import com.example.kaizenapp.data.datasources.SportEventListRemoteDataSource
import com.example.kaizenapp.data.model.SportEvent
import com.example.kaizenapp.data.network.NetworkResult
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SportEventRepository @Inject constructor(
    private val sportEventListDataSource: SportEventListRemoteDataSource
) {

    suspend fun fetchSportEvents(): Flow<NetworkResult<List<SportEvent>>> = flow {
         emit(sportEventListDataSource.fetchSportEvents())
    }


}
