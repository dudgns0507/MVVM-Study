package com.github.dudgns0507.mvvm_cropo.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class Post(
    @Json(name = "userId") val userId: Int = 0,
    @Json(name = "id") val id: Int = 0,
    @Json(name = "title") val title: String = "",
    @Json(name = "body") val body: String = "",
) : Parcelable