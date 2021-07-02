package com.github.dudgns0507.mvvm_cropo.data.repository

import com.github.dudgns0507.core.util.ResultWrapper
import com.github.dudgns0507.core.util.safeApiCall
import com.github.dudgns0507.mvvm_cropo.data.model.Comment
import com.github.dudgns0507.mvvm_cropo.data.model.Post
import com.github.dudgns0507.mvvm_cropo.data.model.RequestPostEdit
import com.github.dudgns0507.mvvm_cropo.data.service.ApiService
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun requestPosts(start: Int, limit: Int): ResultWrapper<List<Post>> =
        safeApiCall { apiService.requestPosts(start, limit) }

    suspend fun requestPost(postId: Int): ResultWrapper<Post> =
        safeApiCall { apiService.requestPost(postId) }

    suspend fun requestPostComments(postId: Int): ResultWrapper<List<Comment>> =
        safeApiCall { apiService.requestPostComments(postId) }

    suspend fun deletePost(postId: Int): ResultWrapper<String> =
        safeApiCall { apiService.deletePost(postId) }

    suspend fun patchPost(postId: Int, post: RequestPostEdit): ResultWrapper<Post> =
        safeApiCall { apiService.patchPost(postId, post) }
}