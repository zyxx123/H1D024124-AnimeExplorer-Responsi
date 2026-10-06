package com.example.animeexplorer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animeexplorer.data.model.Anime
import com.example.animeexplorer.data.remote.ApiClient
import com.example.animeexplorer.data.repository.AnimeRepository
import com.example.animeexplorer.ui.screen.DetailScreen
import com.example.animeexplorer.ui.screen.HomeScreen
import com.example.animeexplorer.ui.viewmodel.AnimeViewModel
import com.example.animeexplorer.ui.viewmodel.AnimeViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    // Initialize repository and viewmodel once
    val repository = AnimeRepository(ApiClient.apiService)
    val factory = AnimeViewModelFactory(repository)
    val viewModel: AnimeViewModel = viewModel(factory = factory)

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToDetail = { anime ->
                    // For simplicity in sharing complex objects without serialization in navigation routes,
                    // we can store it in a temporary local variable or use savedStateHandle.
                    // But in Jetpack Navigation Compose, passing primitive IDs is better.
                    // Since we already fetched the list, we can pass the ID and retrieve it.
                    navController.navigate("detail/\")
                }
            )
        }
        
        composable("detail/{animeId}") { backStackEntry ->
            val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull()
            
            // Find the anime from the viewmodel's current success state
            // If the state is not success (e.g. process killed and recreated), this would be null,
            // but for a simple requirement this is sufficient.
            val currentList = (viewModel.uiState.value as? com.example.animeexplorer.ui.viewmodel.UiState.Success)?.data ?: emptyList()
            val anime = currentList.find { it.id == animeId }
            
            if (anime != null) {
                DetailScreen(
                    anime = anime,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
