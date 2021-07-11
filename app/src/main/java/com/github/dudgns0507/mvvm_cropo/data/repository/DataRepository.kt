package com.github.dudgns0507.mvvm_cropo.data.repository

import com.github.dudgns0507.core.util.network.GenericError
import com.github.dudgns0507.core.util.network.ResultWrapper
import com.github.dudgns0507.mvvm_cropo.data.model.Comment
import com.github.dudgns0507.mvvm_cropo.data.model.Post
import com.github.dudgns0507.mvvm_cropo.data.model.RequestPostEdit
import com.github.dudgns0507.mvvm_cropo.data.service.ApiService
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun requestPosts(start: Int, limit: Int): ResultWrapper<List<Post>, GenericError> = apiService.requestPosts(start, limit)

    suspend fun requestPost(postId: Int): ResultWrapper<Post, GenericError> = apiService.requestPost(postId)

    suspend fun requestPostComments(postId: Int): ResultWrapper<List<Comment>, GenericError> = apiService.requestPostComments(postId)

    suspend fun deletePost(postId: Int): ResultWrapper<String, GenericError> = apiService.deletePost(postId)

    suspend fun patchPost(postId: Int, post: RequestPostEdit): ResultWrapper<Post, GenericError> = apiService.patchPost(postId, post)
}