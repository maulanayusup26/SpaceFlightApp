package com.example.spaceflightapp.articles.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.spaceflightapp.R
import com.example.spaceflightapp.articles.model.ArticleItem
import com.example.spaceflightapp.data.ApiClient
import com.example.spaceflightapp.data.RetrofitInstance
import com.example.spaceflightapp.databinding.ActivityArticleDetailBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id",0)
        val kategori = intent.getStringExtra("kategori")
        if (kategori.equals("Articles")){
            getArtikelDetail(id)
        }else if (kategori.equals("Blogs")) {
            getBlogDetail(id)
        }else if (kategori.equals("Reports")){
            getReportDetail(id)
        }
    }

    private fun formatDateToLocalTime(publishedAt: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = dateFormat.parse(publishedAt)

        val localDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())
        return localDateFormat.format(date)
    }

    // Fungsi untuk mengambil ringkasan (1 kalimat sebelum titik)
    private fun getSummary(summary: String): String {
        val sentenceEndIndex = summary.indexOf(".")
        return if (sentenceEndIndex != -1) {
            summary.substring(0, sentenceEndIndex + 1)
        } else {
            summary
        }
    }

    private fun getArtikelDetail(id: Int) {
        lifecycleScope.launch {
            try {
                var response = RetrofitInstance.api.getArticlesId(id)
                uiView(response.image_url,response.title,response.published_at,response.summary)

            } catch (e: Exception) {
                Log.d("maulana","Gagal ambil data "+e.message)
                Toast.makeText(this@ArticleDetailActivity, "Gagal ambil data", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun getBlogDetail(id: Int) {
        lifecycleScope.launch {
            try {
                var response = RetrofitInstance.api.getBlogsId(id)
                uiView(response.image_url,response.title,response.published_at,response.summary)

            } catch (e: Exception) {
                Log.d("maulana","Gagal ambil data "+e.message)
                Toast.makeText(this@ArticleDetailActivity, "Gagal ambil data", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun getReportDetail(id: Int) {
        lifecycleScope.launch {
            try {
                var response = RetrofitInstance.api.getReportsId(id)
                uiView(response.image_url,response.title,response.published_at,response.summary)

            } catch (e: Exception) {
                Log.d("maulana","Gagal ambil data "+e.message)
                Toast.makeText(this@ArticleDetailActivity, "Gagal ambil data", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    fun uiView (imgUrl:String, title:String, date:String,summary:String){
        binding.tvArticleTitle.text = title
        binding.tvPublishDate.text = formatDateToLocalTime(date)
        binding.tvSummary.text = getSummary(summary)

        // Load gambar
        Glide.with(this@ArticleDetailActivity)
            .load(imgUrl)
            .into(binding.ivArticleImage)
    }
}