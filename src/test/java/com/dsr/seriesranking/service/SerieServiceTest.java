package com.dsr.seriesranking.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;

import com.dsr.seriesranking.dto.SerieDTO;
import com.dsr.seriesranking.dto.SerieRankingDTO;
import com.dsr.seriesranking.model.Serie;
import com.dsr.seriesranking.model.Valoracion;
import com.dsr.seriesranking.repository.SerieRepository;

public class SerieServiceTest {

    @InjectMocks
    private SerieService serieService;

    @Mock
    private SerieRepository serieRepository;

    private static final String uploadDirectory = "src/main/resources/static/assets/";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarSeries() {
        Serie serie = new Serie();
        serie.setNombre("Serie1");
        serie.setPlataforma("Netflix");
        serie.setSinopsis("Sinopsis1");
        serie.setCaratula("caratula.jpg");

        when(serieRepository.findAll()).thenReturn(Collections.singletonList(serie));

        List<SerieDTO> series = serieService.listarSeries();

        assertEquals(1, series.size());
        assertEquals("Serie1", series.get(0).getNombre());
        assertEquals("Netflix", series.get(0).getPlataforma());
        assertEquals("/assets/caratula.jpg", series.get(0).getCaratula());
    }

    @Test
    public void testGuardarSerie() throws IOException {
        SerieDTO serieDTO = new SerieDTO();
        serieDTO.setNombre("Serie2");
        serieDTO.setPlataforma("Amazon Prime");
        serieDTO.setSinopsis("Sinopsis2");

        MockMultipartFile imagen = new MockMultipartFile("imagen", "caratula2.jpg", "image/jpeg", "imagen".getBytes());

        Serie serie = new Serie();
        serie.setNombre(serieDTO.getNombre());
        serie.setPlataforma(serieDTO.getPlataforma());
        serie.setSinopsis(serieDTO.getSinopsis());
        serie.setCaratula(StringUtils.cleanPath(imagen.getOriginalFilename()));

        when(serieRepository.save(any(Serie.class))).thenReturn(serie);

        Serie result = serieService.guardarSerie(serieDTO, imagen);

        assertNotNull(result);
        assertEquals("Serie2", result.getNombre());
        assertEquals("Amazon Prime", result.getPlataforma());
        assertEquals("Sinopsis2", result.getSinopsis());
        assertEquals("caratula2.jpg", result.getCaratula());

        Path path = Paths.get(uploadDirectory + result.getCaratula());
        assertTrue(Files.exists(path));
        
        Files.deleteIfExists(path);
    }

    @Test
    public void testObtenerRankingSeries() {
        Serie serie1 = new Serie();
        serie1.setNombre("Serie1");
        serie1.setPlataforma("Netflix");
        serie1.setSinopsis("Sinopsis1");
        
        Valoracion valoracion1 = new Valoracion();
        valoracion1.setValoracion(4.0);
        Valoracion valoracion2 = new Valoracion();
        valoracion2.setValoracion(8.0);
        
        serie1.setValoraciones(Arrays.asList(valoracion1, valoracion2));

        Serie serie2 = new Serie();
        serie2.setNombre("Serie2");
        serie2.setPlataforma("Hulu");
        serie2.setSinopsis("Sinopsis2");
        
        Valoracion valoracion3 = new Valoracion();
        valoracion3.setValoracion(3.0);
        
        serie2.setValoraciones(Collections.singletonList(valoracion3));

        when(serieRepository.findAll()).thenReturn(Arrays.asList(serie1, serie2));

        List<SerieRankingDTO> ranking = serieService.obtenerRankingSeries();

        assertEquals(2, ranking.size());
        assertEquals("Serie1", ranking.get(0).getNombre());
        assertEquals(4.5, ranking.get(0).getValoracionMedia(), 0.01);
        assertEquals("Serie2", ranking.get(1).getNombre());
        assertEquals(3.0, ranking.get(1).getValoracionMedia(), 0.01);
    }
}



