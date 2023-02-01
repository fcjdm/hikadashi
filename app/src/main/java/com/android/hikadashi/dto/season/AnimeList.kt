package com.android.hikadashi.dto.season


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class AnimeList(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "pagination")
    val pagination: Pagination
) : Parcelable