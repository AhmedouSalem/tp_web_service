package com.tp.webservice.tp_web_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adresse {
	private String pays;
	private String ville;
	private String rue;
	private String numero;
	private String lieuDit;
	private Integer codePostal;
	private Integer longitude;
	private Integer latitude;

	public Adresse() {
		// TODO Auto-generated constructor stub
	}

	public Adresse(String pays, String ville, String rue, String numero, String lieuDit, Integer codePostal, Integer longitude, Integer latitude) {
		this.pays = pays;
		this.ville = ville;
		this.rue = rue;
		this.numero = numero;
		this.lieuDit = lieuDit;
		this.codePostal = codePostal;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Adresse: " + numero + " " + rue + ", " + ville + ", " + pays + ", " + lieuDit + ", " + codePostal + ", " + longitude;
	}
}
