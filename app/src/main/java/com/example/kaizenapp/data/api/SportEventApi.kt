package com.example.kaizenapp.data.api

import com.example.kaizenapp.data.model.SportEvent
import retrofit2.Response
import retrofit2.http.GET

interface SportEventApi {
    @GET("/MockSports/sports.json")
    suspend fun fetchSportEvent(): Response<List<SportEvent>>
}