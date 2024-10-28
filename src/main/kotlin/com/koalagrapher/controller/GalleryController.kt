package com.koalagrapher.controller


import com.koalagrapher.domain.Gallery
import com.koalagrapher.service.GalleryService
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus


@RestController
@RequestMapping("/galleries")
@CrossOrigin(origins = ["http://localhost:3000"])
class GalleryController(private val galleryService: GalleryService) {

    @PostMapping
    fun createGallery(@RequestBody gallery: Gallery): ResponseEntity<Gallery> {
        val createdGallery = galleryService.createGallery(gallery)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGallery)
    }

    @GetMapping("/{id}")
    fun getGallery(@PathVariable id: String , @RequestParam w: Int?): ResponseEntity<Gallery> {
        val gallery = galleryService.getGallery(id)
        return if (gallery != null) ResponseEntity.ok(gallery) else ResponseEntity.notFound().build()
    }

    @PutMapping("/{id}")
    fun updateGallery(@PathVariable id: String, @RequestBody gallery: Gallery): ResponseEntity<Gallery> {
        val updatedGallery = galleryService.updateGallery(id, gallery)
        return if (updatedGallery != null) ResponseEntity.ok(updatedGallery) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteGallery(@PathVariable id: String): ResponseEntity<Void> {
        return if (galleryService.deleteGallery(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }

    @GetMapping
    fun listGalleries(): ResponseEntity<List<Gallery>> {
        return ResponseEntity.ok(galleryService.listGalleries())
    }
}
