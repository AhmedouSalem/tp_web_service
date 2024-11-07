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

    public boolean checkDisponible(String nomHotel, List<Reservation> reservationList, LocalDate dateArrivee, LocalDate dateDepart, Double prixMin, Double prixMax, Integer nbPersonne) {
        if (nbPersonne != this.nbLit) {
            return false;
        }

        // Vérifiez si le prix de la chambre est en dehors de la plage souhaitée
        if (this.prix < prixMin || this.prix > prixMax) {
            // Si le prix n'est pas dans la plage, retournez false pour indiquer que la chambre n'est pas disponible
            return false;
        }

        for (Reservation reservation : reservationList) {
            // Vérifiez si la réservation concerne cette chambre spécifique
            if (Objects.equals(reservation.getChambre().getId(), this.id) && reservation.getHotel().getNom().equalsIgnoreCase(nomHotel)) {
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
        return "Chambre: " + nbLit + " lits, Prix: " + prix;
    }

    public String generateNewId() {
        return UUID.randomUUID().toString(); // Génère un identifiant unique sous forme de chaîne
    }

}
