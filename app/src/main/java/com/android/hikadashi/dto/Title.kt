package com.android.hikadashi.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Title(
    @Json(name = "title")
    val title: String,
    @Json(name = "type")
    val type: String
) : Parcelable