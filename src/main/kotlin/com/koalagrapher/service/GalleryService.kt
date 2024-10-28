package com.koalagrapher.service

import com.koalagrapher.domain.Gallery

interface GalleryService {
    fun createGallery(gallery: Gallery): Gallery
    fun getGallery(id: String): Gallery?
    fun updateGallery(id: String, gallery: Gallery): Gallery?
    fun deleteGallery(id: String): Boolean
    fun listGalleries(): List<Gallery>
}
