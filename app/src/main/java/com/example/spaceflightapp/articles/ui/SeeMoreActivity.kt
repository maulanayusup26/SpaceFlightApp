package com.example.spaceflightapp.articles.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spaceflightapp.R
import com.example.spaceflightapp.articles.adapter.ArticleItemAdapter
import com.example.spaceflightapp.databinding.ActivitySeeMoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeMoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeeMoreBinding
    private lateinit var adapter: ArticleItemAdapter
    private val viewModel: SeeMoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("category") ?: "Articles"

        setupRecyclerView()
        setupObservers()
        setupActions()

        viewModel.loadData(category)
    }

    private fun setupRecyclerView() {
        adapter = ArticleItemAdapter { article ->
            val intent = Intent(this, ArticleDetailActivity::class.java)
            intent.putExtra("id", article.id)
            intent.putExtra("category", article.category)
            startActivity(intent)
        }
        binding.rvArticles.layoutManager = LinearLayoutManager(this)
        binding.rvArticles.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.filteredList.observe(this) {
//            adapter.submitList(it)
        }

        viewModel.newsSites.observe(this) { sites ->
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("All") + sites)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFilter.adapter = spinnerAdapter
        }
    }

    private fun setupActions() {
        binding.etSearch.doAfterTextChanged {
            viewModel.search(it.toString())
        }

        binding.spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val selected = binding.spinnerFilter.selectedItem.toString()
                viewModel.filterByNewsSite(if (selected == "All") null else selected)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.btnSortAsc.setOnClickListener {
            viewModel.sortByDate(ascending = true)
        }

        binding.btnSortDesc.setOnClickListener {
            viewModel.sortByDate(ascending = false)
        }
    }
}
