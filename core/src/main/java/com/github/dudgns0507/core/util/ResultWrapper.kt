package com.github.dudgns0507.core.util

import java.io.IOException

sealed class ResultWrapper<out T : Any, out U : Any> {

    /**
     * Success response with body
     */
    data class Success<out T : Any>(val value: T) : ResultWrapper<T, Nothing>()

    /**
     * Failure response with body
     */
    data class ApiError<U : Any>(val code: Int? = null, val error: U? = null) : ResultWrapper<Nothing, U>()

    /**
     * Network error
     */
    data class NetworkError(val error: IOException) : ResultWrapper<Nothing, Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val throwable: Throwable?) : ResultWrapper<Nothing, Nothing>()
}