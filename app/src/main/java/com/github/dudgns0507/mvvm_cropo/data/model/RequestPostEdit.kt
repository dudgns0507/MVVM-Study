package com.github.dudgns0507.mvvm_cropo.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class RequestPostEdit(
    @Json(name = "title") val title: String = "",
    @Json(name = "body") val body: String = "",
) : Parcelable