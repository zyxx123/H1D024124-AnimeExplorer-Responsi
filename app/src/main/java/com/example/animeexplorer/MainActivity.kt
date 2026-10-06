package com.example.animeexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.animeexplorer.ui.navigation.AppNavigation
import com.example.animeexplorer.ui.theme.AnimeExplorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeExplorerTheme {
                AppNavigation()
            }
        }
    }
}
