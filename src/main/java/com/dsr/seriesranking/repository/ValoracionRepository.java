package com.dsr.seriesranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.dsr.seriesranking.model.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long>{

	List<Valoracion> findAllBySerieId(Long serieId);

}
