package com.example.kaizenapp.data.datasources

import com.example.kaizenapp.data.model.SportEvent
import com.example.kaizenapp.data.network.NetworkResult

interface SportEventDataSource {
    suspend fun fetchSportEvents(): NetworkResult<List<SportEvent>>
}