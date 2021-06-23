package com.github.dudgns0507.mvvm_cropo.data.service

import retrofit2.http.GET

interface ApiService {
    @GET("test")
    suspend fun requestPosts(): String
}