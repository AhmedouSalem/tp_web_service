package com.tp.webservice.tp_web_service.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter

public class Chambre {
    private String id;
    private Integer nbLit;
    private Double prix;

    public Chambre(Integer nbLit, Double prix ){
        this.id = generateNewId();
        this.nbLit = nbLit;
        this.prix = prix;
    }

    public Chambre(String id, Integer nbLit, Double prix ){
        this.id = id;
        this.nbLit = nbLit;
        this.prix = prix;
    }

    public Chambre() {}

    public boolean checkDisponible(String nomHotel, List<Reservation> reservationList, LocalDate dateArrivee, LocalDate dateDepart) {
        for (Reservation reservation : reservationList) {
            // Vérifiez si la réservation concerne cette chambre spécifique
            if (Objects.equals(reservation.getChambre().getId(), this.id) && reservation.getHotel().getNom().equalsIgnoreCase(nomHotel)) {
                for (Hotel hotel : reservation.getAgence().getHotels())
                // Vérification des chevauchements de dates
                if ((dateArrivee.isBefore(reservation.getDateDeparture()) && dateDepart.isAfter(reservation.getReservationDate())) ||
                        (dateArrivee.isAfter(reservation.getReservationDate()) && dateArrivee.isBefore(reservation.getDateDeparture()))) {
                    return false; // La chambre n'est pas disponible
                }
            }
        }
        return true; // La chambre est disponible

    }


    @Override
    public String toString() {
        return "Chambre: " + nbLit + " lits";
    }

    public String generateNewId() {
        return UUID.randomUUID().toString(); // Génère un identifiant unique sous forme de chaîne
    }

}
