package com.android.hikadashi.dto.season


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Trailer(
    @Json(name = "embed_url")
    val embedUrl: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "youtube_id")
    val youtubeId: String
) : Parcelable