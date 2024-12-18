package com.example.kaizenapp.data.datasources

import com.example.kaizenapp.data.api.SportEventApi
import com.example.kaizenapp.data.di.Dispatcher
import com.example.kaizenapp.data.di.SportDispatchers
import com.example.kaizenapp.data.model.SportEvent
import com.example.kaizenapp.data.network.ApiHandler
import com.example.kaizenapp.data.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SportEventListRemoteDataSource @Inject constructor(
    private val sportEventApi: SportEventApi,
    @Dispatcher(SportDispatchers.IO)
    private val ioDispatcher: CoroutineDispatcher
) : SportEventDataSource {

    override suspend fun fetchSportEvents(): NetworkResult<List<SportEvent>> {
        return ApiHandler().handleApi {
            withContext(ioDispatcher) {
                sportEventApi.fetchSportEvent()
            }
        }
    }
}