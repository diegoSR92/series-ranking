package com.dsr.seriesranking.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadController {
	private static final String IMAGE_DIRECTORY = "src/main/resources/static/assets/images/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = IMAGE_DIRECTORY + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            String imageUrl = "/assets/images/" + file.getOriginalFilename();
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen");
        }
    }
}
