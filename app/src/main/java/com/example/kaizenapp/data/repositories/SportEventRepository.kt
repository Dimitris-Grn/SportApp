package com.example.kaizenapp.data.repositories

import android.util.Log
import com.example.kaizenapp.data.datasources.SportEventListRemoteDataSource
import com.example.kaizenapp.data.model.SportEvent
import com.example.kaizenapp.data.model.SportEventFinal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SportEventRepository @Inject constructor(
    private val sportEventListDataSource: SportEventListRemoteDataSource
) {
    fun testFetchSportEvents(): Flow<SportEventListRemoteDataSource.NetworkResult<List<SportEventFinal>>> = flow {
        emit(sportEventListDataSource.fetchSportEvents())
    }
}
