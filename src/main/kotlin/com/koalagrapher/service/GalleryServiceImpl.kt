package com.koalagrapher.service

import com.koalagrapher.domain.Gallery
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Service
class GalleryServiceImpl : GalleryService {
    private val galleries = ConcurrentHashMap<String, Gallery>()
    private var nextId = 1L

    override fun createGallery(gallery: Gallery): Gallery {
        val newId = UUID.randomUUID().toString()
        val newGallery = gallery.copy(id = newId)
        galleries[newId] = newGallery
        return newGallery
    }

    override fun getGallery(id: String): Gallery? = galleries[id]

    override fun updateGallery(id: String, gallery: Gallery): Gallery? {
        return if (galleries.containsKey(id)) {
            val updatedGallery = gallery.copy(id = id)
            galleries[id] = updatedGallery
            updatedGallery
        } else null
    }

    override fun deleteGallery(id: String): Boolean = galleries.remove(id) != null

    override fun listGalleries(): List<Gallery> = galleries.values.toList()
}