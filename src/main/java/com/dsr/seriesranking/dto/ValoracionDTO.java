package com.dsr.seriesranking.dto;

import com.dsr.seriesranking.model.Serie;

public class ValoracionDTO {
	
	private String autor;
	private double valoracion;
	
	public ValoracionDTO(String autor, double valoracion, Serie serie) {
		this.autor = autor;
		this.valoracion = valoracion;
	}
	
	public ValoracionDTO() {
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public Double getValoracion() {
		return valoracion;
	}
	
	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}
	
}
