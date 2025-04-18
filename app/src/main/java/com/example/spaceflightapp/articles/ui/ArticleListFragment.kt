package com.example.spaceflightapp.articles.ui

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightapp.articles.adapter.CategoryAdapter
import com.example.spaceflightapp.articles.model.CategoryItem
import com.example.spaceflightapp.data.Auth0Api
import com.example.spaceflightapp.data.RetrofitInstance
import com.example.spaceflightapp.databinding.FragmentArticleListBinding
import com.example.spaceflightapp.repository.ArticleRepository
import com.example.spaceflightapp.viewmodel.ArticleViewModel
import com.example.spaceflightapp.viewmodel.ArticleViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar

@AndroidEntryPoint
class ArticleListFragment : Fragment() {

    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = ArticleRepository(RetrofitInstance.api)
        val factory = ArticleViewModelFactory(repository)
        articleViewModel = ViewModelProvider(this, factory).get(ArticleViewModel::class.java)

        setGreeting()
        categoryAdapter = CategoryAdapter(emptyList()) { categoryName ->
            // Aksi saat tombol See More diklik

            Toast.makeText(requireContext(), "See more: $categoryName", Toast.LENGTH_SHORT).show()
        }
        binding.rvCategoryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }

        articleViewModel.categories.observe(viewLifecycleOwner) { categoryItems ->
            categoryAdapter.updateData(categoryItems)
        }

        articleViewModel.fetchCategories()
    }

    private fun setGreeting() {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when (currentHour) {
            in 0..11 -> "Good Morning"
            in 12..17 -> "Good Afternoon"
            else -> "Good Evening"
        }
        binding.tvUcapan.text = greeting
        binding.tvUsername.text = "Maulana Yusup" // replace with actual username
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
