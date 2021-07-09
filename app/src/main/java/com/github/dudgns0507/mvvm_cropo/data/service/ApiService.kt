package com.github.dudgns0507.mvvm_cropo.data.service

import com.github.dudgns0507.core.util.GenericError
import com.github.dudgns0507.core.util.ResultWrapper
import com.github.dudgns0507.mvvm_cropo.data.model.Comment
import com.github.dudgns0507.mvvm_cropo.data.model.Post
import com.github.dudgns0507.mvvm_cropo.data.model.RequestPostEdit
import retrofit2.http.*

interface ApiService {
    @GET("posts")
    suspend fun requestPosts(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): ResultWrapper<List<Post>, GenericError>

    @GET("posts/{postId}")
    suspend fun requestPost(
        @Path("postId") postId: Int
    ): ResultWrapper<Post, GenericError>

    @GET("posts/{postId}/comments")
    suspend fun requestPostComments(
        @Path("postId") postId: Int
    ): ResultWrapper<List<Comment>, GenericError>

    @DELETE("posts/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: Int
    ): ResultWrapper<String, GenericError>

    @PATCH("posts/{postId}")
    suspend fun patchPost(
        @Path("postId") postId: Int,
        @Body post: RequestPostEdit
    ): ResultWrapper<Post, GenericError>
}