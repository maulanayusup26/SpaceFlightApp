package com.example.spaceflightapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.fragment.app.viewModels
import com.example.spaceflightapp.articles.model.ArticleItem
import com.example.spaceflightapp.articles.model.CategoryItem
import com.example.spaceflightapp.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val _categories = MutableLiveData<List<CategoryItem>>()
    val categories: LiveData<List<CategoryItem>> = _categories

    fun fetchCategories() {
        viewModelScope.launch {
            val result = repository.fetchAllCategories()
            _categories.value = result
        }
    }
}
