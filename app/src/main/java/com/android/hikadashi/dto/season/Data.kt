package com.android.hikadashi.dto.season


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Data(
    @Json(name = "aired")
    val aired: Aired?=null,
    @Json(name = "airing")
    val airing: Boolean?=null,
    @Json(name = "approved")
    val approved: Boolean?=null,
    @Json(name = "background")
    val background: String?=null,
    @Json(name = "broadcast")
    val broadcast: Broadcast?=null,
    @Json(name = "demographics")
    val demographics: List<Demographic>?=null,
    @Json(name = "duration")
    val duration: String?=null,
    @Json(name = "episodes")
    val episodes: Int?=null,
    @Json(name = "explicit_genres")
    val explicitGenres: List<ExplicitGenre>?=null,
    @Json(name = "favorites")
    val favorites: Int?=null,
    @Json(name = "genres")
    val genres: List<Genre>?=null,
    @Json(name = "images")
    val images: Images?=null,
    @Json(name = "licensors")
    val licensors: List<Licensor>?=null,
    @Json(name = "mal_id")
    val malId: String,
    @Json(name = "members")
    val members: Int?=null,
    @Json(name = "popularity")
    val popularity: Int?=null,
    @Json(name = "producers")
    val producers: List<Producer>?=null,
    @Json(name = "rank")
    val rank: Int?=null,
    @Json(name = "rating")
    val rating: String?=null,
    @Json(name = "score")
    val score: Float?=null,
    @Json(name = "scored_by")
    val scoredBy: Int?=null,
    @Json(name = "season")
    val season: String?=null,
    @Json(name = "source")
    val source: String?=null,
    @Json(name = "status")
    val status: String?=null,
    @Json(name = "studios")
    val studios: List<Studio>?=null,
    @Json(name = "synopsis")
    val synopsis: String?=null,
    @Json(name = "themes")
    val themes: List<Theme>?=null,
    @Json(name = "title")
    val title: String?=null,
    @Json(name = "title_english")
    val titleEnglish: String?=null,
    @Json(name = "title_japanese")
    val titleJapanese: String?=null,
    @Json(name = "title_synonyms")
    val titleSynonyms: List<String>?=null,
    @Json(name = "titles")
    val titles: List<Title>?=null,
    @Json(name = "trailer")
    val trailer: Trailer?=null,
    @Json(name = "type")
    val type: String?=null,
    @Json(name = "url")
    val url: String?=null,
    @Json(name = "year")
    val year: Int?=null
) : Parcelable