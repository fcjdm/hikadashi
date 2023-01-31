package com.android.hikadashi.dto.anime


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Prop(
    @Json(name = "from")
    val from: From,
    @Json(name = "string")
    val string: String,
    @Json(name = "to")
    val to: To
) : Parcelable