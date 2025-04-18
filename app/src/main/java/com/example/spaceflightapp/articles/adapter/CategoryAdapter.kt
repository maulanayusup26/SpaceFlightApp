package com.example.spaceflightapp.articles.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spaceflightapp.R
import com.example.spaceflightapp.articles.model.ArticleItem
import com.example.spaceflightapp.articles.model.CategoryItem
import com.example.spaceflightapp.articles.ui.ArticleDetailActivity

class CategoryAdapter(
    private var categories: List<CategoryItem>,
    private val onSeeMoreClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCategoryTitle: TextView = itemView.findViewById(R.id.tv_category_title)
        val rvItems: RecyclerView = itemView.findViewById(R.id.rv_articles)
        val btnSeeMore: TextView = itemView.findViewById(R.id.btn_see_more)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_section, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]

        holder.tvCategoryTitle.text = category.categoryName

        // Setup horizontal recycler
        holder.rvItems.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
//        holder.rvItems.adapter = ArticleItemAdapter(category.items)
        holder.rvItems.adapter = ArticleItemAdapter(category.items) { selectedItem ->
            val context = holder.itemView.context
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra("id", selectedItem.id) // Kirim ID ke DetailActivity
            intent.putExtra("kategori", category.categoryName) // Kirim ID ke DetailActivity
            context.startActivity(intent)
        }


        holder.btnSeeMore.setOnClickListener {
            onSeeMoreClick(category.categoryName)
        }
    }

    override fun getItemCount(): Int = categories.size

    fun updateData(newCategories: List<CategoryItem>) {
        categories = newCategories
        notifyDataSetChanged()
    }
}


