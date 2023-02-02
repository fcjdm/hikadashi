package com.android.hikadashi.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Webp(
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "large_image_url")
    val largeImageUrl: String,
    @Json(name = "small_image_url")
    val smallImageUrl: String
) : Parcelable