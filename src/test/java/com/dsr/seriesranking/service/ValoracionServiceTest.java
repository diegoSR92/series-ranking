package com.dsr.seriesranking.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dsr.seriesranking.dto.ValoracionDTO;
import com.dsr.seriesranking.model.Serie;
import com.dsr.seriesranking.model.Valoracion;
import com.dsr.seriesranking.repository.SerieRepository;
import com.dsr.seriesranking.repository.ValoracionRepository;

public class ValoracionServiceTest {

    @InjectMocks
    private ValoracionService valoracionService;

    @Mock
    private ValoracionRepository valoracionRepository;

    @Mock
    private SerieRepository serieRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarValoracion() {
        Long serieId = 1L;

        Serie serie = new Serie();
        serie.setId(serieId);

        ValoracionDTO valoracionDTO = new ValoracionDTO();
        valoracionDTO.setAutor("Autor1");
        valoracionDTO.setValoracion(4.0);

        when(serieRepository.findById(serieId)).thenReturn(Optional.of(serie));
        when(valoracionRepository.save(any(Valoracion.class))).thenAnswer(i -> i.getArgument(0));

        Valoracion resultado = valoracionService.guardarValoracion(serieId, valoracionDTO);

        assertNotNull(resultado);
        assertEquals("Autor1", resultado.getAutor());
        assertEquals(4.0, resultado.getValoracion());
        assertEquals(serie, resultado.getSerie());
    }

    @Test
    public void testGuardarValoracion_SerieNoEncontrada() {
        Long serieId = 1L;

        ValoracionDTO valoracionDTO = new ValoracionDTO();
        valoracionDTO.setAutor("Autor1");
        valoracionDTO.setValoracion(4.0);

        when(serieRepository.findById(serieId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            valoracionService.guardarValoracion(serieId, valoracionDTO);
        });

        assertEquals("Serie no encontrada con ID: " + serieId, exception.getMessage());
    }

    @Test
    public void testCalcularValoracionMedia() {
        Long serieId = 1L;

        Valoracion valoracion1 = new Valoracion();
        valoracion1.setValoracion(3.0);
        Valoracion valoracion2 = new Valoracion();
        valoracion2.setValoracion(5.0);
        List<Valoracion> valoraciones = Arrays.asList(valoracion1, valoracion2);

        when(valoracionRepository.findAllBySerieId(serieId)).thenReturn(valoraciones);

        Double media = valoracionService.calcularValoracionMedia(serieId);

        assertEquals(4.0, media, 0.01);
    }

    @Test
    public void testCalcularValoracionMedia_SinValoraciones() {
        Long serieId = 1L;

        when(valoracionRepository.findAllBySerieId(serieId)).thenReturn(Collections.emptyList());

        Double media = valoracionService.calcularValoracionMedia(serieId);

        assertEquals(0.0, media);
    }
}

