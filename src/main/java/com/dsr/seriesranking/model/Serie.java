package com.dsr.seriesranking.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Serie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String plataforma;
	private String sinopsis;
	
	@Lob
	private String caratula;
	
	@OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
	private List<Valoracion> valoraciones = new ArrayList<>();
	
	public Serie(String nombre, String plataforma, String caratula, String sinopsis, List<Valoracion> valoraciones ) {
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.caratula = caratula;
		this.sinopsis = sinopsis;
		this.valoraciones = valoraciones;
	}
	
	public Serie() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}
	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String filename) {
		this.caratula = filename;
	}
	
	
}
