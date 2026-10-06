# Anime Explorer

## Deskripsi
Anime Explorer adalah aplikasi mobile Android sederhana yang dibuat untuk menampilkan daftar anime secara dinamis. Aplikasi ini dirancang untuk memenuhi requirement tugas responsi dengan menerapkan arsitektur modern Android (Jetpack Compose, MVVM, dan Material Design 3).

## Teknologi
- **Bahasa:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Navigasi:** Navigation Compose
- **Arsitektur:** MVVM (Model, View, ViewModel, Repository)
- **Networking:** Retrofit + OkHttp
- **Serialization:** Kotlinx Serialization
- **API:** Tenrai API (https://api.tenrai.org/v1)

## Arsitektur
Aplikasi ini menerapkan alur data yang searah sesuai konsep MVVM:
**UI** (Compose Screen) $\rightarrow$ **ViewModel** (Mengelola State) $\rightarrow$ **Repository** (Mengelola pemanggilan API) $\rightarrow$ **API Service** (Retrofit) $\rightarrow$ **Tenrai API**

Response dari API kemudian dikembalikan secara reaktif:
API $\rightarrow$ Repository $\rightarrow$ ViewModel (Memperbarui UiState) $\rightarrow$ UI (Update tampilan secara otomatis)

## API
Aplikasi menggunakan **Tenrai API** tanpa memerlukan otentikasi.
- **Base URL:** https://api.tenrai.org/v1
- **Endpoint yang digunakan:** /anime (mengembalikan daftar anime beserta detailnya seperti judul, rating, tahun rilis, dan episode).

## Fitur
- Menampilkan daftar anime secara dinamis menggunakan LazyColumn.
- Mengambil data dari REST API (Tenrai API).
- State management lengkap: Loading state, Success state (Data), dan Error state.
- Fitur "Retry" jika gagal mengambil data dari API (misal internet mati).
- Anime Detail Screen yang dapat diakses dengan menekan salah satu item dari daftar.

## Struktur Project
- data/model/: Berisi Kotlin data class untuk memetakan response JSON (Anime, AnimeResponse).
- data/remote/: Berisi konfigurasi Retrofit (ApiClient) dan interface endpoint (TenraiApiService).
- data/repository/: Berisi AnimeRepository yang menghubungkan API dan ViewModel.
- ui/viewmodel/: Berisi AnimeViewModel dan UiState (Sealed class untuk Loading, Success, Error).
- ui/screen/: Berisi layar Compose (HomeScreen, DetailScreen).
- ui/navigation/: Berisi AppNavigation untuk navigasi antar screen.
- ui/theme/: Berisi Custom Theme, Custom Typography, dan Color palette (Material 3).

## Cara Menjalankan
1. Buka project di **Android Studio** (Koala atau versi yang lebih baru disarankan).
2. Tunggu proses **Gradle Sync** sampai selesai.
3. Pastikan Anda memiliki emulator yang sedang berjalan atau hubungkan device Android secara fisik.
4. Tekan tombol **Run** (Shift+F10) pada Android Studio.

## Screenshot
*(Screenshot dapat ditambahkan di sini)*
