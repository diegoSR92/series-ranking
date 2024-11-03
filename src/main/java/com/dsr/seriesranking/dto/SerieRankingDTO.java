package com.dsr.seriesranking.dto;

public class SerieRankingDTO {
	
	private String caratula;
    private String nombre;
    private String plataforma;
    private String sinopsis;
    private Double valoracionMedia;

    public SerieRankingDTO(String caratula, String nombre, String plataforma, String sinopsis, Double valoracionMedia) {
        this.caratula = caratula;
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.sinopsis = sinopsis;
        this.valoracionMedia = valoracionMedia;
    }

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
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

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public Double getValoracionMedia() {
		return valoracionMedia;
	}

	public void setValoracionMedia(Double valoracionMedia) {
		this.valoracionMedia = valoracionMedia;
	}
    
    
}
