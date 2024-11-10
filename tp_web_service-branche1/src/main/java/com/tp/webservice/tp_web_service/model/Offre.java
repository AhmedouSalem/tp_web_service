package com.tp.webservice.tp_web_service.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import static com.tp.webservice.tp_web_service.util.DateUtils.convertToLocalDate;

@Getter
@Setter
public class Offre implements Serializable {
    private String idOffre;
    private Chambre chambre; // Représente le type de chambre
    private XMLGregorianCalendar dateDisponibilite; // Date à laquelle la chambre est disponible
    private double prix; // Prix de la chambre

    public Offre(Chambre chambre, XMLGregorianCalendar dateDisponibilite, double prix) {
        if (chambre == null || dateDisponibilite == null) {
            throw new IllegalArgumentException("Chambre et date de disponibilité ne peuvent pas être null");
        }
        this.idOffre = generateNewId();
        this.chambre = chambre;
        this.dateDisponibilite = dateDisponibilite;
        this.prix = prix;
    }

    @Override
    public String toString() {
        LocalDate localDate = convertToLocalDate(dateDisponibilite);
        return "Offre: " + idOffre + ", " + chambre.toString() + ", Prix: " + prix + ", Disponibilité: " + dateDisponibilite;
    }

    public String generateNewId() {
        return UUID.randomUUID().toString(); // Génère un identifiant unique sous forme de chaîne
    }
}
