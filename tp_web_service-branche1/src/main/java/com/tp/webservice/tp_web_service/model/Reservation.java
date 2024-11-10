package com.tp.webservice.tp_web_service.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Reservation implements Serializable {
    private static int numReservation = 1;
    private Client client;
    private Agence agence;
    private Hotel hotel;
    private LocalDate reservationDate;
    private LocalDate dateDeparture;
    private Chambre chambre;

    public Reservation(Client client, Agence agence, Hotel hotel, Chambre chambre, LocalDate reservationDate, LocalDate dateDeparture) {
        numReservation = numReservation++;
        this.client = client;
        this.agence = agence;
        this.hotel = hotel;
        this.chambre = chambre;
        this.reservationDate = reservationDate;
        this.dateDeparture = dateDeparture;
    }


    public String generateNewId() {
        return UUID.randomUUID().toString(); // Génère un identifiant unique sous forme de chaîne
    }
}