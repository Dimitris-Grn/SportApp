package com.example.kaizenapp.data.datasources

import android.util.Log
import com.example.kaizenapp.data.api.SportEventApi
import com.example.kaizenapp.data.di.Dispatcher
import com.example.kaizenapp.data.di.SportDispatchers
import com.example.kaizenapp.data.model.SportEvent
import com.example.kaizenapp.data.model.SportResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SportEventListRemoteDataSource @Inject constructor(
    private val sportEventApi: SportEventApi,
    @Dispatcher(SportDispatchers.IO)
    private val ioDispatcher: CoroutineDispatcher
) : SportEventDataSource {

    override suspend fun fetchSportEvents() {
        withContext(ioDispatcher) {
            val response = sportEventApi.fetchSportEvent()
            if(response.isSuccessful) {
                val respoStr = response.body().toString()
                val gson = Gson()
                var personListType = object : TypeToken<List<SportResponse>>() {}.type
                var people: List<SportResponse> = gson.fromJson(respoStr, personListType)

                //Log.d("RemoteDAta", "Fetch sport event $rawResponse")
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

