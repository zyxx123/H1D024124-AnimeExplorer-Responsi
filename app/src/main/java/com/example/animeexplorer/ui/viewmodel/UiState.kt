package com.example.animeexplorer.ui.viewmodel

import com.example.animeexplorer.data.model.Anime

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
