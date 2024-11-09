package com.tp.webservice.tp_web_service.webservice;

import com.tp.webservice.tp_web_service.ManageException.ChambreNotFoundException;
import com.tp.webservice.tp_web_service.ManageException.HotelWebServiceException;
import com.tp.webservice.tp_web_service.ManageException.LoginException;
import com.tp.webservice.tp_web_service.ManageException.VilleNotFoundException;
import com.tp.webservice.tp_web_service.model.*;

import javax.jws.WebService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "com.tp.webservice.tp_web_service.webservice.HotelWebServiceConsulting")
public class HotelWebServiceConsultingImplement implements HotelWebServiceConsulting {
    private ArrayList<Agence> agences;
    private ArrayList<Hotel> hotels;
    private ArrayList<Reservation> reservationList;
    List<Chambre> chambreList = new ArrayList<>();

    public HotelWebServiceConsultingImplement() {
        // ces exemples sont pris sur internet pour le test
        Adresse adresseParis = new Adresse("France", "Paris", "Avenue de l'Opéra", "5", "Quartier de l'Opéra", 75002, 23350, 48867);
        Adresse adresseMontpellier = new Adresse("France", "Montpellier", "Place de la Comédie", "1", "Centre-ville", 34000, 38760, 43611);
        Adresse adresseMontpellier1 = new Adresse("France", "Montpellier", "Faubourg Boutonnet", "119", "Boutonnet", 34000, 38760, 43611);
        Adresse adresseNimes = new Adresse("France", "Nîmes", "Rue de la République", "3", "Centre-ville", 30000, 43600, 43836);
        Adresse adressePerpignan = new Adresse("France", "Perpignan", "Rue de l'Ange", "2", "Centre historique", 66000, 28950, 42688);
        Adresse adresseNancy = new Adresse("France", "Nancy", "Rue Stanislas", "54", "Quartier Stanislas", 54000, 61750, 48694);


        // Création des chambres
        chambreList.add(new Chambre(1, 100.0));
        chambreList.add(new Chambre(2, 150.0));
        chambreList.add(new Chambre(3, 200.0));
        chambreList.add(new Chambre(4, 300.0));
        chambreList.add(new Chambre(5, 400.0));
        chambreList.add(new Chambre(6, 500.0));
        chambreList.add(new Chambre(7, 600.0));
        chambreList.add(new Chambre(8, 700.0));
        chambreList.add(new Chambre(9, 800.0));

        // Ajouter des hôtels à la liste
        hotels.add(new Hotel("Hôtel de Paris", 5, chambreList, adresseParis));
        hotels.add(new Hotel("Hôtel de Montpellier", 4, chambreList, adresseMontpellier));
        hotels.add(new Hotel("Nîmes", 5, chambreList, adresseNimes));
        hotels.add(new Hotel("Perpignan", 4, chambreList, adressePerpignan));
        hotels.add(new Hotel("Nancy", 4, chambreList, adresseNancy));
        hotels.add(new Hotel("Hôtel de Montpellier 1 ", 4, chambreList, adresseMontpellier1));
        //reservationList.add(new Reservation(new Client(), hotels.get(5), chambreList.get(2), LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)));

        this.agences = new ArrayList<>();
        this.hotels = new ArrayList<>();
        this.reservationList = new ArrayList<>();
    }
    @Override
    public List<Offre> consulterLesOffres(Login login, String ville, LocalDate dateDebutReservation, LocalDate dateFinReservation, Integer nbPersonne) throws LoginException, VilleNotFoundException, ChambreNotFoundException {
        Agence agencePartenaire = checkLogin(login);
        List<Offre> offresDisponibles = new ArrayList<>();

        // Vérifier s'il y a des offres dans la ville concerné (ville de destination)
        for(Hotel h : agencePartenaire.getHotels()) {
            if (!h.getAdresse().getVille().equals(ville)) {
                throw new VilleNotFoundException("Il y a pas des offres disponibles dans cette ville");
            }
            // Logique pour obtenir les chambres disponibles
            for(Chambre ch : h.getChambres()) {
                if (!(ch.getNbLit() == nbPersonne)) {
                    throw new ChambreNotFoundException("Il y a des chambres pour ces critères");
                }
                // Logique pour la disponibilité selon les dates spécifié
                if (ch.checkDisponible(h.getNom(), reservationList, dateDebutReservation, dateFinReservation)){
                    // Créer une nouvelle offre et l'ajouter à la liste
                    Offre offre = new Offre(ch, dateDebutReservation, ch.getPrix());
                    offresDisponibles.add(offre);
                };
            }
        }
        return offresDisponibles;
    }



    public Agence checkLogin(Login login) throws LoginException {
        Agence agencePartenaire = null;
        for(Agence ag : agences) {
            if (!ag.validerAgenceLogin(login)) {
                    throw new LoginException("Identifiant ou mot de passe incorrect");
            }
            agencePartenaire = ag;
        }
        return agencePartenaire;
    }
}
