package com.example.spaceflightapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightapp.articles.model.ArticleItem
import com.example.spaceflightapp.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SeeMoreViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private var allItems = listOf<ArticleItem>()
    val filteredList = MutableLiveData<List<ArticleItem>>()
    val newsSites = MutableLiveData<List<String>>()

    fun loadData(category: String) {
        viewModelScope.launch {
            val items = repository.fetchCategoryItems(category)
            allItems = items
            filteredList.value = items
            newsSites.value = items.map { it.newsSite }.distinct()
        }
    }

    fun search(query: String) {
        filteredList.value = allItems.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    fun filterByNewsSite(site: String?) {
        filteredList.value = allItems.filter {
            site == null || it.newsSite == site
        }
    }

    fun sortByDate(ascending: Boolean) {
        filteredList.value = filteredList.value?.sortedBy {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            sdf.parse(it.publishedAt)
        }?.let {
            if (!ascending) it.reversed() else it
        }
    }
}

