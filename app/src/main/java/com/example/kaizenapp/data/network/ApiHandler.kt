package com.example.kaizenapp.data.network

import retrofit2.HttpException
import retrofit2.Response

interface ApiHandler {

    suspend fun handleApi(
        execute: suspend () -> Response<*>
    ): NetworkResult2<Any> {
        return try {
            val response = execute()
            if (response.isSuccessful) {
                NetworkResult2.Success(response.code(), response.body())
            } else {
                NetworkResult2.Error(response.code(), response.errorBody()?.string())
            }
        } catch (e: HttpException) {
            NetworkResult2.Error(e.code(), e.message())
        } catch (e: Throwable) {
            NetworkResult2.Exception(e)
        }
    }
}
sealed class NetworkResult2<out T : Any> {
    data class Success<out T : Any>(val code: Int, val apiData: Any?) : NetworkResult2<T>()
    data class Error<out T : Any>(val code: Int, val errorMsg: String?) : NetworkResult2<T>()
    data class Exception(val e: Throwable) : NetworkResult2<Nothing>()
}