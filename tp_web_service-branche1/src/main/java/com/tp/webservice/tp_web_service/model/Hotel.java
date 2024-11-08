package com.tp.webservice.tp_web_service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Hotel {
    private String nom;
    private int nbEtoile;
    private Adresse adresse;
    private List<Chambre> chambres;

    public Hotel(String nom, int nbEtoile, List<Chambre> chambres, Adresse adresse) {
        this.nom = nom;
        this.nbEtoile = nbEtoile;
        this.adresse = adresse;
        this.chambres = new ArrayList<>();
        // Ajout de chambres codées en dur
        this.chambres.addAll(chambres);
    }
    Hotel(){}
}
