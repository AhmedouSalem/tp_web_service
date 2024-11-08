package com.tp.webservice.tp_web_service.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Reservation {
    private static int numReservation = 1;
    private Client client;
    private Agence agence;
    private LocalDate reservationDate;
    private LocalDate dateDeparture;
    private Chambre chambre;

    public Reservation(Client client, Agence agence, Chambre chambre, LocalDate reservationDate, LocalDate dateDeparture) {
        numReservation = numReservation++;
        this.client = client;
        this.agence = agence;
        this.chambre = chambre;
        this.reservationDate = reservationDate;
        this.dateDeparture = dateDeparture;
    }

    public boolean confirmerReservation() {
        return numReservation == 1;
    }
}