package com.example.animeexplorer.data.remote

import com.example.animeexplorer.data.model.AnimeResponse
import retrofit2.http.GET

interface TenraiApiService {
    @GET("anime")
    suspend fun getAnimeList(): AnimeResponse
}
