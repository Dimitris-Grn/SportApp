package com.example.kaizenapp.data.datasources

interface SportEventDataSource {
    suspend fun fetchSportEvents()
}