package com.android.hikadashi.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class From(
    @Json(name = "day")
    val day: Int,
    @Json(name = "month")
    val month: Int,
    @Json(name = "year")
    val year: Int
) : Parcelable