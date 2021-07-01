package com.github.dudgns0507.core.util

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class ApiError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResultWrapper<Nothing>()

    data class NetworkError(val error: IOException) : ResultWrapper<Nothing>()
    data class UnknownError(val throwable: Throwable) : ResultWrapper<Nothing>()
}

data class ErrorResponse(
    val error_description: String,
    val causes: Map<String, String> = emptyMap()
)

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(Dispatchers.Default) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.ApiError(code, errorResponse)
                }
                is IOException -> ResultWrapper.NetworkError(throwable)
                else -> {
                    ResultWrapper.UnknownError(throwable)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}

fun <T> ResultWrapper<T>.isSuccess(): ResultWrapper.Success<T>? {
    return when (this) {
        is ResultWrapper.NetworkError -> null
        is ResultWrapper.ApiError -> null
        is ResultWrapper.UnknownError -> null
        is ResultWrapper.Success<T> -> this
        else -> null
    }
}