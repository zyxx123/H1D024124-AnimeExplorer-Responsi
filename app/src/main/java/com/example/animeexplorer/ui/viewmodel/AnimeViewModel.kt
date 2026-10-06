package com.example.animeexplorer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.animeexplorer.data.model.Anime
import com.example.animeexplorer.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class AnimeViewModel(private val repository: AnimeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Anime>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Anime>>> = _uiState.asStateFlow()

    init {
        getAnimeList()
    }

    fun getAnimeList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val list = repository.getAnimeList()
                if (list.isEmpty()) {
                    _uiState.value = UiState.Error("Data kosong")
                } else {
                    _uiState.value = UiState.Success(list)
                }
            } catch (e: IOException) {
                _uiState.value = UiState.Error("Kesalahan jaringan, periksa koneksi internet Anda.")
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Terjadi kesalahan yang tidak diketahui")
            }
        }
    }
}

class AnimeViewModelFactory(private val repository: AnimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
