package com.tp.webservice.tp_web_service.model;

import java.time.LocalDate;
import java.util.UUID;

public class Offre {
    private String idOffre;
    private Chambre chambre; // Représente le type de chambre
    private LocalDate dateDisponibilite; // Date à laquelle la chambre est disponible
    private double prix; // Prix de la chambre

    public Offre(Chambre chambre, LocalDate dateDisponibilite, double prix) {
        this.idOffre = generateNewId();
        this.chambre = chambre;
        this.dateDisponibilite = dateDisponibilite;
        this.prix = prix;
    }

    public Offre() {}

    @Override
    public String toString() {
        return "Offre: " + idOffre + ", " + chambre.toString() + ", Prix: " + prix + ", Disponibilité: " + dateDisponibilite;
    }

    public String generateNewId() {
        return UUID.randomUUID().toString(); // Génère un identifiant unique sous forme de chaîne
    }
}
