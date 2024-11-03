package com.dsr.seriesranking.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dsr.seriesranking.dto.ValoracionDTO;
import com.dsr.seriesranking.model.Valoracion;
import com.dsr.seriesranking.service.ValoracionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ValoracionController.class)
public class ValoracionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ValoracionService valoracionService;

    @Autowired
    private ObjectMapper objectMapper;  // Para convertir objetos a JSON

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAgregarValoracion() throws Exception {
        // Simula una nueva valoración
        Valoracion nuevaValoracion = new Valoracion();
        nuevaValoracion.setId(1L);
        nuevaValoracion.setAutor("Miguel");
        nuevaValoracion.setValoracion(5.0);

        // Simula el DTO de la valoración de entrada
        ValoracionDTO valoracionDTO = new ValoracionDTO();
        valoracionDTO.setAutor("Pepito");
        valoracionDTO.setValoracion(7.0);

        // Configura el comportamiento simulado de guardarValoracion
        when(valoracionService.guardarValoracion(eq(1L), any(ValoracionDTO.class))).thenReturn(nuevaValoracion);

        // Realiza la solicitud POST y verifica la respuesta
        mockMvc.perform(post("/api/series/1/valoraciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(valoracionDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.comentario", is("Excelente serie")))
                .andExpect(jsonPath("$.puntuacion", is(5)));
    }
}

