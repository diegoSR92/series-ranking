package com.dsr.seriesranking.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dsr.seriesranking.dto.SerieDTO;
import com.dsr.seriesranking.dto.SerieRankingDTO;
import com.dsr.seriesranking.dto.ValoracionDTO;
import com.dsr.seriesranking.model.Serie;
import com.dsr.seriesranking.model.Valoracion;
import com.dsr.seriesranking.repository.SerieRepository;

@Service
public class SerieService {

    private static final String uploadDirectory = "src/main/resources/static/assets/";
	@Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> listarSeries() {
        return serieRepository.findAll().stream()
        		.map(this::convertToDTO)
        		.collect(Collectors.toList());
    }

    public Serie guardarSerie(SerieDTO serieDTO, MultipartFile imagen) throws IOException {
        Serie serie = new Serie();
        serie.setNombre(serieDTO.getNombre());
        serie.setPlataforma(serieDTO.getPlataforma());
        serie.setSinopsis(serieDTO.getSinopsis());

        if (!imagen.isEmpty()) {
            String filename = StringUtils.cleanPath(imagen.getOriginalFilename());
            Path path = Paths.get(uploadDirectory + filename);

            Files.createDirectories(path.getParent());
            Files.write(path, imagen.getBytes());

            serie.setCaratula(filename);
        }

        return serieRepository.save(serie);
    }

    public List<SerieRankingDTO> obtenerRankingSeries() {
        return serieRepository.findAll().stream()
            .map(serie -> new SerieRankingDTO(
                serie.getCaratula(),
                serie.getNombre(),
                serie.getPlataforma(),
                serie.getSinopsis(),
                serie.getValoraciones().stream()
                    .mapToDouble(Valoracion::getValoracion).average().orElse(0.0)
            ))
            .sorted(Comparator.comparing(SerieRankingDTO::getValoracionMedia).reversed())
            .collect(Collectors.toList());
    }
    
    private SerieDTO convertToDTO(Serie serie) {
        SerieDTO dto = new SerieDTO();

        dto.setNombre(serie.getNombre());
        dto.setPlataforma(serie.getPlataforma());
        dto.setSinopsis(serie.getSinopsis());

        if (serie.getCaratula() != null) {
            String imageUrl = "/assets/" + serie.getCaratula();
            dto.setCaratula(imageUrl);
        }

        List<ValoracionDTO> valoracionesDTO = serie.getValoraciones().stream()
            .map(this::convertToValoracionDTO)
            .collect(Collectors.toList());
        dto.setValoraciones(valoracionesDTO);

        return dto;
    }
    
    private ValoracionDTO convertToValoracionDTO (Valoracion valoracion) {
    	
    	ValoracionDTO dto = new ValoracionDTO();
    	
    	dto.setAutor(valoracion.getAutor());
    	dto.setValoracion(valoracion.getValoracion());

    	return dto;
    }
    
    
}
