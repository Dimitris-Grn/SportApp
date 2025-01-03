package com.example.kaizenapp.data.datasources

import com.example.kaizenapp.data.api.SportEventApi
import com.example.kaizenapp.data.di.Dispatcher
import com.example.kaizenapp.data.di.SportDispatchers
import com.example.kaizenapp.data.model.SportData
import com.example.kaizenapp.data.model.SportEvent
import com.example.kaizenapp.data.model.SportEventFinal
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

typealias SportEventList = ArrayList<LinkedTreeMap<String, SportEvent>>

class SportEventListRemoteDataSource @Inject constructor(
    private val sportEventApi: SportEventApi,
    @Dispatcher(SportDispatchers.IO)
    private val ioDispatcher: CoroutineDispatcher
) : SportEventDataSource {


    override suspend fun fetchSportEvents(): NetworkResult {
        return withContext(ioDispatcher) {
            val response = sportEventApi.fetchSportEvent()
            if (response.isSuccessful) {
                val respBody = response.body()

                val list = safeCast<ArrayList<LinkedTreeMap<String, SportData>>>(respBody)
                val finalList = arrayListOf<SportEventFinal>()
                if (list != null) {
                    list.forEach {
                        var listEvent: List<SportEvent> = listOf()
                        val sportName = safeCast<String>(it["d"])
                        val treeMapEvents =
                            safeCast<ArrayList<LinkedTreeMap<String, SportEvent>>>(it["e"])

                        treeMapEvents?.forEach { event ->
                            listEvent = ArrayList(event.values)
                        }
                        val sportEventFinal = SportEventFinal(sportName, listEvent)
                        finalList.add(sportEventFinal)
                    }
                    NetworkResult.Success(finalList)
                } else {
                    NetworkResult.Error(response.code(), response.message())
                }
            } else {
                NetworkResult.Error(response.code(), response.message())
            }
        }
    }

    private inline fun <reified T> safeCast(obj: Any?): T? {
        return obj as? T
    }

    sealed class NetworkResult {
        data class Success(val apiData: Any?) : NetworkResult()
        data class Error(val code: Int, val errorMsg: String?) : NetworkResult()
    }
}

