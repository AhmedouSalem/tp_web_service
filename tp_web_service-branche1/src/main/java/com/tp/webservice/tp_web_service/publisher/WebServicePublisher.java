package com.tp.webservice.tp_web_service.publisher;

import com.tp.webservice.tp_web_service.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebServicePublisher implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // Création d'un client
        Client client = new Client("Ahmed", "Ali", "ahmed@example.com");

        // Création d'une adresse
        Adresse adresse = new Adresse("France", "Paris", "Avenue de l'Opéra", "5", "Quartier de l'Opéra", 75002, 23350, 48867);

        // Création des chambres
        List<Chambre> chambreList = new ArrayList<>();
        chambreList.add(new Chambre( 1, 100.0));
        chambreList.add(new Chambre( 2, 150.0));
        chambreList.add(new Chambre(3, 200.0));
        chambreList.add(new Chambre( 4, 300.0));
        // Création d'un hôtel
        Hotel hotel = new Hotel("Hôtel de Paris", 5,chambreList, adresse);

        // Afficher les détails de l'hôtel
        hotel.afficherDetails();

        // Création d'une réservation
        Chambre chambre = hotel.getChambres().get(0); // Prendre la première chambre
        //Reservation reservation = new Reservation(1, client, hotel, chambre);
        //reservation.creerReservation();

        // Afficher les détails de la réservation
        //reservation.afficherDetails();
    }
}
