package com.dsr.seriesranking.controller;

import com.dsr.seriesranking.dto.ValoracionDTO;
import com.dsr.seriesranking.model.Valoracion;
import com.dsr.seriesranking.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/series/{serieId}/valoraciones")
@CrossOrigin(origins = "http://localhost:4200")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    // Añadir una valoración a una serie específica
    @PostMapping
    public ResponseEntity<Valoracion> agregarValoracion(
            @PathVariable Long serieId,
            @RequestBody ValoracionDTO valoracionDTO) {
        Valoracion nuevaValoracion = valoracionService.guardarValoracion(serieId, valoracionDTO);
        return ResponseEntity.ok(nuevaValoracion);
    }
}
