package com.android.hikadashi.dto.season


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Pagination(
    @Json(name = "has_next_page")
    val hasNextPage: Boolean,
    /*@Json(name = "items")
    val items: Items,*/
    @Json(name = "last_visible_page")
    val lastVisiblePage: Int
) : Parcelable