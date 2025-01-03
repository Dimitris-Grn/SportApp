package com.example.kaizenapp.data.datasources

import com.example.kaizenapp.data.datasources.SportEventListRemoteDataSource.NetworkResult
import com.example.kaizenapp.data.model.SportEventFinal

interface SportEventDataSource {
    suspend fun fetchSportEvents() : NetworkResult<List<SportEventFinal>>
}