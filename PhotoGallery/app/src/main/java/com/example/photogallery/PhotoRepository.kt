package com.example.photogallery

class PhotoRepository {
    private val api = buildPicsumApi()

    suspend fun fetchPhotos(): List<GalleryItem> = api.fetchPhotos(page = 1, limit = 100)
}
