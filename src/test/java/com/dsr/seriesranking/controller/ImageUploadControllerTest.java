package com.dsr.seriesranking.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ImageUploadController.class)
public class ImageUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ImageUploadController imageUploadController;

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/assets/images/";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadImage_Success() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "imagen.jpg", MediaType.IMAGE_JPEG_VALUE, "test image content".getBytes());

        mockMvc.perform(multipart("/upload")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("/assets/images/imagen.jpg")));

        File savedFile = new File(IMAGE_DIRECTORY + file.getOriginalFilename());
        assertTrue(savedFile.exists());

        if (savedFile.exists()) {
            savedFile.delete();
        }
    }

	@Test
    public void testUploadImage_Failure() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "imagen.jpg", MediaType.IMAGE_JPEG_VALUE, "test image content".getBytes());

        try (MockedStatic<File> mockedFile = mockStatic(File.class)) {
            mockedFile.when(() -> new File(IMAGE_DIRECTORY + file.getOriginalFilename())).thenThrow(new IOException("Error al guardar la imagen"));

            mockMvc.perform(multipart("/upload")
                    .file(file))
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().string("Error al guardar la imagen"));
        }
    }
}

