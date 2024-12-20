package com.example.kaizenapp.data.datasources

import com.example.kaizenapp.data.api.SportEventApi
import com.example.kaizenapp.data.di.Dispatcher
import com.example.kaizenapp.data.di.SportDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SportEventListRemoteDataSource @Inject constructor(
    private val sportEventApi: SportEventApi,
    @Dispatcher(SportDispatchers.IO)
    private val ioDispatcher: CoroutineDispatcher
) : SportEventDataSource {

    override suspend fun fetchSportEvents(): NetworkResult {
        return withContext(ioDispatcher) {
            val response = sportEventApi.fetchSportEvent()
            if(response.isSuccessful) {
                val respBody = response.body()
                NetworkResult.Success(respBody)
            } else {
                NetworkResult.Error(response.code(), response.message())
            }
        }
    }

    sealed class NetworkResult {
        data class Success(val apiData: Any?) : NetworkResult()
        data class Error(val code: Int, val errorMsg: String?) : NetworkResult()
    }
}

