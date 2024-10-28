package com.koalagrapher.controller

import com.koalagrapher.service.ImageService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = ["http://localhost:3000"])
class ImageController(private val imageService: ImageService) {

    @PostMapping("/upload/{id}")
    fun uploadImage(@PathVariable id: String, @RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        val savedImage = imageService.saveImage(id, file)
        return ResponseEntity.ok(id)
    }

    @GetMapping("/{id}")
    fun downloadImage(
        @PathVariable id: String,
        @RequestParam(required = false, defaultValue = "500") width: Int,
        @RequestParam(required = false, defaultValue = "500") height: Int
    ): ResponseEntity<ByteArray> {
        val resizedImage: BufferedImage = imageService.loadImage(id, width, height)

        val outputStream = ByteArrayOutputStream()
        ImageIO.write(resizedImage, "jpg", outputStream)

        return ResponseEntity.ok()
            //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$filename\"") //FIXME
            .contentType(MediaType.IMAGE_JPEG)
            .body(outputStream.toByteArray())
    }
}
