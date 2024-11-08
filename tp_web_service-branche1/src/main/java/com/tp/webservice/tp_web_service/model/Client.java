package com.tp.webservice.tp_web_service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Client {
    private String nom;
    private String prenom;
    private String email;
    private List<CarteCredit> carteCredits;
    private List<Reservation> reservations;

    public Client() {}
    public Client(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.reservations = new ArrayList<>();
        this.carteCredits = new ArrayList<>();
    }

    public String effectuerReservation() {
        return null;
    }
}
