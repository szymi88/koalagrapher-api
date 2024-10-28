package com.koalagrapher.service

import net.coobird.thumbnailator.Thumbnails
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileOutputStream

@Service
class ImageService {

    private val storageDir = File("images")

    init {
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }
    }

    fun saveImage(id: String, file: MultipartFile) {
//        val id = file.originalFilename ?: throw IllegalArgumentException("Invalid file")
        val targetFile = File(storageDir, id)

        file.inputStream.use { input ->
            FileOutputStream(targetFile).use { output ->
                input.copyTo(output)
            }
        }
    }

    fun loadImage(id: String, width: Int, height: Int): BufferedImage {
        val file = File(storageDir, id)
        return Thumbnails.of(file)
            .size(width, height)
            .asBufferedImage()
    }
}
