package com.dsr.seriesranking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsr.seriesranking.dto.ValoracionDTO;
import com.dsr.seriesranking.model.Serie;
import com.dsr.seriesranking.model.Valoracion;
import com.dsr.seriesranking.repository.SerieRepository;
import com.dsr.seriesranking.repository.ValoracionRepository;

@Service
public class ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private SerieRepository serieRepository;

    
    // Guarda una nueva valoración para una serie específica.
    public Valoracion guardarValoracion(Long serieId, ValoracionDTO valoracionDTO) {
        
        Optional<Serie> serieOptional = serieRepository.findById(serieId);
        if (!serieOptional.isPresent()) {
            throw new RuntimeException("Serie no encontrada con ID: " + serieId);
        }

        Valoracion valoracion = new Valoracion();
        valoracion.setSerie(serieOptional.get());
        valoracion.setAutor(valoracionDTO.getAutor());
        valoracion.setValoracion(valoracionDTO.getValoracion());

        return valoracionRepository.save(valoracion);
    }

    
    // Calcula la valoración media de una serie en función de sus valoraciones.
    public Double calcularValoracionMedia(Long serieId) {
        List<Valoracion> valoraciones = valoracionRepository.findAllBySerieId(serieId);

        if (valoraciones.isEmpty()) {
            return 0.0;
        }

        double suma = valoraciones.stream()
                .mapToDouble(Valoracion::getValoracion)
                .sum();

        return suma / valoraciones.size();
    }
}