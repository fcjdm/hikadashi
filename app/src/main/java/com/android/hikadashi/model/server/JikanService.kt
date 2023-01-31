package com.android.hikadashi.model.server

import com.android.hikadashi.dto.anime.Anime
import retrofit2.http.GET
interface JikanService {

    @GET("/top/anime")
    suspend fun getTopAnimes(): List<Anime>

}