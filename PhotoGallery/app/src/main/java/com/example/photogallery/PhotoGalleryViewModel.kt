package com.example.photogallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PhotoGalleryViewModel : ViewModel() {
    private val repository = PhotoRepository()

    private val _allItems = MutableStateFlow<List<GalleryItem>>(emptyList())
    private val _searchQuery = MutableStateFlow("")

    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val galleryItems: StateFlow<List<GalleryItem>> = combine(_allItems, _searchQuery) { items, query ->
        if (query.isBlank()) items
        else items.filter { it.author.contains(query, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        fetchPhotos()
    }

    fun fetchPhotos() {
        viewModelScope.launch {
            try {
                _allItems.value = repository.fetchPhotos()
            } catch (_: Exception) { }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
}
