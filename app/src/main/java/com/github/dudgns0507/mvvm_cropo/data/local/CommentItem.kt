package com.github.dudgns0507.mvvm_cropo.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "comment_items")
data class CommentItem(
    @PrimaryKey
    val id: Int = 0,
    val postId: Int = 0,
    val name: String = "",
    val email: String = "",
    val body: String = "",
)