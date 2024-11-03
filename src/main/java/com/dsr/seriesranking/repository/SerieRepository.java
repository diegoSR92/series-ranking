package com.dsr.seriesranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsr.seriesranking.dto.SerieDTO;
import com.dsr.seriesranking.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long>{

	Serie save(SerieDTO serieDTO);

}
