package com.android.hikadashi.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Broadcast(
    @Json(name = "day")
    val day: String,
    @Json(name = "string")
    val string: String,
    @Json(name = "time")
    val time: String,
    @Json(name = "timezone")
    val timezone: String
) : Parcelable