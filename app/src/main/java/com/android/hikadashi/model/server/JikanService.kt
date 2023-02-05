package com.android.hikadashi.model.server

import com.android.hikadashi.dto.AnimeList
import retrofit2.http.GET
import retrofit2.http.Query

interface JikanService {

    @GET("top/anime")
    suspend fun getTopAnimes(): AnimeList

    @GET("recommendations/anime")
    suspend fun getRecommendAnimes(): AnimeList

    @GET("seasons/upcoming")
    suspend fun getUpcomingAnime(): AnimeList

    @GET("seasons/now")
    suspend fun getAiringAnime(): AnimeList

    @GET("anime")
    suspend fun getSearchAnime(@Query("q")name: String,
        @Query("sfw")sfw: Boolean): AnimeList




}