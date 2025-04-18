package com.example.spaceflightapp.articles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spaceflightapp.R
import com.example.spaceflightapp.articles.model.ArticleItem

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

        holder.articleTitle.text = article.title
        Glide.with(holder.itemView.context)
            .load(article.image_url)
            .into(holder.articleImage)

        holder.itemView.setOnClickListener {
            onItemClick(article)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }
}
