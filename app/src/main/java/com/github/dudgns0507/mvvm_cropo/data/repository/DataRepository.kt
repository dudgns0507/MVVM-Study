package com.github.dudgns0507.mvvm_cropo.data.repository

import com.github.dudgns0507.core.util.ResultWrapper
import com.github.dudgns0507.core.util.safeApiCall
import com.github.dudgns0507.mvvm_cropo.data.service.ApiService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun requestPosts(): ResultWrapper<String> =
        safeApiCall(Dispatchers.IO) { apiService.requestPosts() }
}