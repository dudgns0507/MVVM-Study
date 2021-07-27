package com.github.dudgns0507.mvvm_cropo.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "post_items")
data class PostItem (
    @PrimaryKey
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val body: String = ""
)