package com.github.dudgns0507.core.util

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class SafeCallAdapter<T : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<T, Call<ResultWrapper<T>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<ResultWrapper<T>> {
        return SafeCall(call, errorBodyConverter)
    }
}