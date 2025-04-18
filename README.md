# Spaceflight News App

Aplikasi Android sederhana yang menampilkan artikel dari Spaceflight News API. Dibuat menggunakan Kotlin, MVVM Architecture, Retrofit, dan Glide.

## 📱 Fitur yang Sudah Diimplementasikan

### ✅ Halaman Artikel (Main Page)
- Menampilkan daftar berita dari 3 kategori: **Articles**, **Blogs**, dan **Reports**.
- Setiap kategori ditampilkan secara horizontal (nested RecyclerView).
- Terdapat tombol **"See More"** di tiap kategori (belum diarahkan ke halaman detail).
- Data diambil dari API `https://api.spaceflightnewsapi.net/v4/`.

### ✅ Halaman Detail Artikel
- Dibuka ketika item diklik dari halaman artikel.
- Menampilkan informasi lengkap artikel:
  - **Gambar artikel**
  - **Judul**
  - **Tanggal publish** dalam format lokal seperti `16 April 2024, 16:00`
  - **Ringkasan** artikel berupa satu kalimat pertama dari summary API.
- Menggunakan `Glide` untuk menampilkan gambar.
- Mengambil data berdasarkan ID melalui endpoint detail:  
  `https://api.spaceflightnewsapi.net/v4/blogs/{id}`

### 🛠️ Arsitektur & Teknologi
- Kotlin
- MVVM Architecture
- Retrofit2 + Coroutine
- Glide
- ViewBinding

## 🚧 Fitur yang Belum Selesai (Kendala Waktu)
- **Halaman See More** dengan fitur pencarian, filter, dan sorting.
- **BottomSheet Filter & Sort**.
- **Database lokal (Room)** untuk menyimpan user login.
- **SharedPreferences** atau cache data untuk persistensi list terakhir.

