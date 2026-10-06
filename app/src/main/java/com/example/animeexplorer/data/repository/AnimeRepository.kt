package com.example.animeexplorer.data.repository

import com.example.animeexplorer.data.model.Anime
import com.example.animeexplorer.data.remote.TenraiApiService

class AnimeRepository(private val apiService: TenraiApiService) {
    suspend fun getAnimeList(): List<Anime> {
        return apiService.getAnimeList().data
    }
}
