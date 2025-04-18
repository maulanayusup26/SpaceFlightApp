package com.example.spaceflightapp.articles.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spaceflightapp.R
import com.example.spaceflightapp.articles.model.Article
import com.example.spaceflightapp.articles.model.ArticleItem
import com.example.spaceflightapp.articles.ui.ArticleDetailActivity

class ArticleItemAdapter(private val articleList: List<ArticleItem>,
                         private val onItemClick: (ArticleItem) -> Unit) : RecyclerView.Adapter<ArticleItemAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleTitle: TextView = itemView.findViewById(R.id.tv_title)
        val articleImage: ImageView = itemView.findViewById(R.id.img_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_card, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleList[position]

        // Set article title
        holder.articleTitle.text = article.title

        // Load article image using Glide or any image loading library
        Glide.with(holder.itemView.context)
            .load(article.image_url) // Assuming article.imageUrl contains the URL of the image
            .into(holder.articleImage)

        holder.itemView.setOnClickListener {
            onItemClick(article) // Panggil callback ke luar
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }
}
