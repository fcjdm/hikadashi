package com.android.hikadashi.dto.anime


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Aired(
    @Json(name = "from")
    val from: String,
    @Json(name = "prop")
    val prop: Prop,
    @Json(name = "to")
    val to: String
) : Parcelable