package com.example.animeexplorer.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeResponse(
    @SerialName("data")
    val data: List<Anime> = emptyList()
)

@Serializable
data class Anime(
    @SerialName("mal_id")
    val id: Int = 0,
    @SerialName("title")
    val title: String = "Unknown",
    @SerialName("score")
    val score: Double? = null,
    @SerialName("year")
    val year: Int? = null,
    @SerialName("episodes")
    val episodes: Int? = null,
    @SerialName("synopsis")
    val synopsis: String? = null
)
