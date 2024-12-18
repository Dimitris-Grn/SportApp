package com.example.kaizenapp.data.network

import retrofit2.HttpException
import retrofit2.Response

class ApiHandler {
    suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = execute()
            if (response.isSuccessful) {
                NetworkResult.Success(response.code(), response.body()!!)
            } else {
                NetworkResult.Error(response.code(), response.errorBody()?.string())
            }
        } catch (e: HttpException) {
            NetworkResult.Error(e.code(), e.message())
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }
}
sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val code: Int, val apiData: T) : NetworkResult<T>()
    data class Error<out T : Any>(val code: Int, val errorMsg: String?) : NetworkResult<T>()
    data class Exception(val e: Throwable) : NetworkResult<Nothing>()
}