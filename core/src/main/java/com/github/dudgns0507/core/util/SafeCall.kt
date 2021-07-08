package com.github.dudgns0507.core.util

import com.squareup.moshi.Moshi
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.*
import java.io.IOException

class SafeCall<T : Any, E : Any>(
    private val delegate: Call<T>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<ResultWrapper<T>> {

    override fun enqueue(callback: Callback<ResultWrapper<T>>) {
        return delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                try {
                    body?.let {
                        callback.onResponse(this@SafeCall, Response.success(ResultWrapper.Success(it)))
                    } ?: kotlin.run {
                        callback.onResponse(this@SafeCall, Response.success(ResultWrapper.UnknownError(null)))
                    }
                } catch (throwable: Throwable) {
                    callback.onResponse(this@SafeCall, Response.success(getError(throwable)))
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                callback.onResponse(this@SafeCall, Response.success(getError(throwable)))
            }
        })
    }

    private fun getError(throwable: Throwable): ResultWrapper<T> {
        return when (throwable) {
            is HttpException -> ResultWrapper.ApiError(throwable.code(), convertErrorBody(throwable))
            is IOException -> ResultWrapper.NetworkError(throwable)
            else -> ResultWrapper.UnknownError(throwable)
        }
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = SafeCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<ResultWrapper<T>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout {
        return timeout()
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
}