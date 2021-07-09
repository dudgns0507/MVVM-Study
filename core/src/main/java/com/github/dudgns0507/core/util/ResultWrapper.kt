package com.github.dudgns0507.core.util

import java.io.IOException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class ApiError(val code: Int? = null, val error: ErrorResponse? = null) : ResultWrapper<Nothing>()
    data class NetworkError(val error: IOException) : ResultWrapper<Nothing>()
    data class UnknownError(val throwable: Throwable?) : ResultWrapper<Nothing>()
}

data class ErrorResponse(
    val error_description: String,
    val causes: Map<String, String> = emptyMap()
)