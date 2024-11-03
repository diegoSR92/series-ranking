package com.dsr.seriesranking.dto;

import java.util.List;

public class SerieDTO {
	
	private String nombre;
	private String plataforma;
	private String caratula;
	private String sinopsis;
	private List<ValoracionDTO> valoraciones;
	
	public SerieDTO(String nombre, String plataforma, String caratula, String sinopsis,List<ValoracionDTO>valoraiones) {
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.caratula = caratula;
		this.sinopsis = sinopsis;
		this.valoraciones = valoraiones;
	}

	public SerieDTO() {
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getCaratula() {
		return caratula;
	}
	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public List<ValoracionDTO> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<ValoracionDTO> valoraciones) {
		this.valoraciones = valoraciones;
	}
	
}
