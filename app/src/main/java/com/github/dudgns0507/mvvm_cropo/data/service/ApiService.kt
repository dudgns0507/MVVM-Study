package com.github.dudgns0507.mvvm_cropo.data.service

import com.github.dudgns0507.mvvm_cropo.data.model.Comment
import com.github.dudgns0507.mvvm_cropo.data.model.Post
import com.github.dudgns0507.mvvm_cropo.data.model.RequestPostEdit
import retrofit2.http.*

interface ApiService {
    @GET("posts")
    suspend fun requestPosts(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): List<Post>

    @GET("posts/{postId}")
    suspend fun requestPost(
        @Path("postId") postId: Int
    ): Post

    @GET("posts/{postId}/comments")
    suspend fun requestPostComments(
        @Path("postId") postId: Int
    ): List<Comment>

    @DELETE("posts/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: Int
    ): String

    @PATCH("posts/{postId}")
    suspend fun patchPost(
        @Path("postId") postId: Int,
        @Body post: RequestPostEdit
    ): Post
}