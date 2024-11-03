package com.dsr.seriesranking.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import com.dsr.seriesranking.dto.SerieDTO;
import com.dsr.seriesranking.dto.SerieRankingDTO;
import com.dsr.seriesranking.model.Serie;
import com.dsr.seriesranking.service.SerieService;

@WebMvcTest(SerieController.class)
public class SerieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SerieService serieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarSeries() throws Exception {
        
        SerieDTO serie1 = new SerieDTO("Serie1", "Plataforma1", "Sinopsis1", null, null);
        SerieDTO serie2 = new SerieDTO("Serie2", "Plataforma2", "Sinopsis2", null, null);
        List<SerieDTO> series = Arrays.asList(serie1, serie2);

        when(serieService.listarSeries()).thenReturn(series);

        mockMvc.perform(get("/api/series")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Serie1")))
                .andExpect(jsonPath("$[1].nombre", is("Serie2")));
    }

    @Test
    public void testAgregarSerie() throws Exception {
       
        Serie serie = new Serie("SerieNueva", "PlataformaNueva", "SinopsisNueva", null, null);

        MockMultipartFile imagen = new MockMultipartFile("imagen", "imagen.jpg", MediaType.IMAGE_JPEG_VALUE, "imagen".getBytes());

        when(serieService.guardarSerie(any(SerieDTO.class), any(MultipartFile.class))).thenReturn(serie);

        mockMvc.perform(multipart("/api/series/add")
                .file(imagen)
                .param("nombre", "SerieNueva")
                .param("plataforma", "PlataformaNueva")
                .param("sinopsis", "SinopsisNueva")
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("SerieNueva")))
                .andExpect(jsonPath("$.plataforma", is("PlataformaNueva")))
                .andExpect(jsonPath("$.sinopsis", is("SinopsisNueva")));
    }

    @Test
    public void testObtenerRankingSeries() throws Exception {
        
        SerieRankingDTO serieRanking1 = new SerieRankingDTO("Serie1", null, null, null, 9.5);
        SerieRankingDTO serieRanking2 = new SerieRankingDTO("Serie2", null, null, null, 8.7);
        List<SerieRankingDTO> ranking = Arrays.asList(serieRanking1, serieRanking2);

        when(serieService.obtenerRankingSeries()).thenReturn(ranking);

        mockMvc.perform(get("/api/series/ranking")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Serie1")))
                .andExpect(jsonPath("$[0].rating", is(9.5)))
                .andExpect(jsonPath("$[1].nombre", is("Serie2")))
                .andExpect(jsonPath("$[1].rating", is(8.7)));
    }
}


