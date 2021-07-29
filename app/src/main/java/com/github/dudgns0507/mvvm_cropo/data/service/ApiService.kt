package com.github.dudgns0507.mvvm_cropo.data.service

import com.github.dudgns0507.core.util.network.GenericError
import com.github.dudgns0507.core.util.network.ResultWrapper
import com.github.dudgns0507.mvvm_cropo.data.model.ResponseComment
import com.github.dudgns0507.mvvm_cropo.data.model.ResponsePost
import com.github.dudgns0507.mvvm_cropo.data.model.RequestPostEdit
import retrofit2.http.*

interface ApiService {
    @GET("posts")
    suspend fun requestPosts(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): ResultWrapper<List<ResponsePost>, GenericError>

    @GET("posts/{postId}")
    suspend fun requestPost(
        @Path("postId") postId: Int
    ): ResultWrapper<ResponsePost, GenericError>

    @GET("posts/{postId}/comments")
    suspend fun requestPostComments(
        @Path("postId") postId: Int
    ): ResultWrapper<List<ResponseComment>, GenericError>

    @DELETE("posts/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: Int
    ): ResultWrapper<String, GenericError>

    @PATCH("posts/{postId}")
    suspend fun patchPost(
        @Path("postId") postId: Int,
        @Body post: RequestPostEdit
    ): ResultWrapper<ResponsePost, GenericError>
}