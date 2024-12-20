package com.example.kaizenapp.data.repositories

import com.example.kaizenapp.data.datasources.SportEventListRemoteDataSource
import com.example.kaizenapp.data.network.NetworkResult2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SportEventRepository @Inject constructor(
    private val sportEventListDataSource: SportEventListRemoteDataSource
) {

    suspend fun fetchSportEvents(): Flow<NetworkResult2<String>> = flow {
       //  emit(sportEventListDataSource.fetchSportEvents())
    }

    suspend fun testFetchSportEvents() {
        sportEventListDataSource.fetchSportEvents()
    }




}
