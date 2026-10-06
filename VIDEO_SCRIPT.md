# Script Penjelasan Kode (Responsi Android)

Halo Pak/Bu, hari ini saya akan menjelaskan kode untuk aplikasi Anime Explorer yang sudah saya buat sesuai dengan requirement responsi.

## 1. Struktur Project
Aplikasi ini dibangun menggunakan arsitektur MVVM murni dengan Jetpack Compose. Jika dilihat dari foldernya, saya membagi kodenya menjadi package data dan ui. 
- data menyimpan model, emote (untuk API), dan epository.
- ui menyimpan screen, iewmodel, 	heme, dan 
avigation.

## 2. Model & Networking (API)
Pertama, untuk mengambil data dari Tenrai API, saya membuat TenraiApiService menggunakan Retrofit, dan mengarah ke base URL https://api.tenrai.org/v1. 
Lalu, respon JSON dipetakan ke dalam data class Kotlin yaitu AnimeResponse dan Anime dengan menggunakan Kotlinx Serialization. Data class ini memiliki properti nullable untuk safety jika API mengembalikan null.

## 3. Repository
Setelah API siap, saya membuat AnimeRepository. Tugasnya sederhana: menjembatani Retrofit dan ViewModel. Repository ini mengambil fungsi getAnimeList() dari ApiService dan mereturn datanya berupa List<Anime>.

## 4. ViewModel dan UiState
Selanjutnya di layer UI, saya membuat AnimeViewModel. Di sini saya mendefinisikan UiState menggunakan sealed class yang isinya ada Loading, Success, dan Error.
Di dalam ViewModel, state awal diset sebagai Loading. Kemudian kita memanggil repository di dalam iewModelScope.launch (Coroutines). 
- Jika sukses, state berubah ke Success berisi list anime. 
- Jika gagal seperti koneksi putus, catch block akan menangkap error tersebut dan state diubah menjadi Error.

## 5. UI (Home Screen & Detail Screen)
Di sisi UI, saya sepenuhnya menggunakan **Jetpack Compose** dan **Material Design 3**, tanpa menggunakan XML sama sekali.
Di HomeScreen, kita meng-observe state dari ViewModel. 
- Saat Loading, kita tampilkan CircularProgressIndicator.
- Saat Error, kita tampilkan pesan error dan tombol "Retry" yang bisa dipencet untuk request API ulang.
- Saat Success, data ditampilkan menggunakan LazyColumn. Setiap item menampilkan Judul, Rating, Tahun, dan Episode.

Ketika item diklik, aplikasi pindah ke DetailScreen melalui AppNavigation. Di DetailScreen, data spesifik yang di-pass ditampilkan lebih rinci, dan saya juga menambahkan sinopsis jika tersedia.

## 6. Theme dan Styling
Untuk tampilannya, saya mengimplementasikan Custom Theme dan Custom Typography di file Theme.kt, Color.kt, dan Type.kt. Saya memastikan tampilannya clean, terbaca dengan baik, dan sudah support Material 3, bukan cuma desain polosan.

## Kesimpulan (Alur Data)
Secara singkat, alur aplikasinya: UI meminta data ke ViewModel, ViewModel menyuruh Repository, Repository mengambilnya ke Tenrai API. Saat response masuk, ViewModel mengupdate UiState, lalu Compose di Home Screen me-recompose (menggambar ulang) UI secara dinamis.

Sekian penjelasan kode dari saya, terima kasih.
