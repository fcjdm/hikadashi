package com.android.hikadashi.model.server

import com.android.hikadashi.dto.season.AnimeList
import retrofit2.http.GET
interface JikanService {

    @GET("top/anime")
    suspend fun getTopAnimes(): AnimeList

    @GET("recommendations/anime")
    suspend fun getRecommendAnimes(): AnimeList

    @GET("seasons/upcoming")
    suspend fun getUpcomingAnime(): AnimeList

    @GET("seasons/now")
    suspend fun getAiringAnime(): AnimeList



}