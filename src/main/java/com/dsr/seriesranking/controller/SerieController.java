package com.dsr.seriesranking.controller;


import com.dsr.seriesranking.dto.SerieDTO;
import com.dsr.seriesranking.dto.SerieRankingDTO;
import com.dsr.seriesranking.model.Serie;
import com.dsr.seriesranking.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/series")
@CrossOrigin(origins = "http://localhost:4200")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping
    public ResponseEntity<List<SerieDTO>> listarSeries() {
        List<SerieDTO> series = serieService.listarSeries();
        return ResponseEntity.ok(series);
    }

    // Añadir una nueva serie
    @PostMapping("/add")
    public ResponseEntity<Serie> agregarSerie(
            @RequestParam("nombre") String nombre,
            @RequestParam("plataforma") String plataforma,
            @RequestParam("sinopsis") String sinopsis,
            @RequestParam("caratula") MultipartFile imagen) throws IOException {
        
        SerieDTO serieDTO = new SerieDTO();
        serieDTO.setNombre(nombre);
        serieDTO.setPlataforma(plataforma);
        serieDTO.setSinopsis(sinopsis);

        Serie serie = serieService.guardarSerie(serieDTO, imagen);
        
        return ResponseEntity.ok(serie);
    }

    // Obtener el ranking de series
    @GetMapping("/ranking")
    public ResponseEntity<List<SerieRankingDTO>> obtenerRankingSeries() {
        List<SerieRankingDTO> ranking = serieService.obtenerRankingSeries();
        return ResponseEntity.ok(ranking);
    }
}
