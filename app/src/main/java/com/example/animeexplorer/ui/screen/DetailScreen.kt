package com.example.animeexplorer.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animeexplorer.data.model.Anime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    anime: Anime,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Anime") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = anime.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            
            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoColumn(label = "Rating", value = anime.score?.toString() ?: "N/A")
                InfoColumn(label = "Tahun Rilis", value = anime.year?.toString() ?: "N/A")
                InfoColumn(label = "Episode", value = anime.episodes?.toString() ?: "N/A")
            }

            if (!anime.synopsis.isNullOrEmpty()) {
                HorizontalDivider()
                Text(
                    text = "Sinopsis",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = anime.synopsis,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun InfoColumn(label: String, value: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
    }
}
