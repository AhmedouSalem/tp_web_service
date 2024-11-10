package com.tp.webservice.tp_web_service.webservice;

import com.tp.webservice.tp_web_service.ManageException.ChambreNotFoundException;
import com.tp.webservice.tp_web_service.ManageException.HotelWebServiceException;
import com.tp.webservice.tp_web_service.ManageException.LoginException;
import com.tp.webservice.tp_web_service.ManageException.VilleNotFoundException;
import com.tp.webservice.tp_web_service.model.*;
import com.tp.webservice.tp_web_service.util.DateUtils;

import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "com.tp.webservice.tp_web_service.webservice.HotelWebServiceConsulting")
public class HotelWebServiceConsultingImplement implements HotelWebServiceConsulting {
    private ArrayList<Agence> agences;
    private ArrayList<Hotel> hotels;
    private ArrayList<Reservation> reservationList;
    List<Chambre> chambreList = new ArrayList<>();  // Déclaration de la liste de chambres
    List<Offre> offresDisponibles = new ArrayList<>();  // Déclaration de la liste des offres disponibles


    public HotelWebServiceConsultingImplement() {
        this.agences = new ArrayList<>();
        this.hotels = new ArrayList<>();
        this.reservationList = new ArrayList<>();

        // adresse des hôtels
        Adresse adresseParis = new Adresse("France", "Paris", "Avenue de l'Opéra", "5", "Quartier de l'Opéra", 75002, 23350, 48867);
        Adresse adresseMontpellier = new Adresse("France", "Montpellier", "Place de la Comédie", "1", "Centre-ville", 34000, 38760, 43611);
        Adresse adresseMontpellier1 = new Adresse("France", "Montpellier", "Faubourg Boutonnet", "119", "Boutonnet", 34000, 38760, 43611);
        Adresse adresseNimes = new Adresse("France", "Nîmes", "Rue de la République", "3", "Centre-ville", 30000, 43600, 43836);
        Adresse adressePerpignan = new Adresse("France", "Perpignan", "Rue de l'Ange", "2", "Centre historique", 66000, 28950, 42688);
        Adresse adresseNancy = new Adresse("France", "Nancy", "Rue Stanislas", "54", "Quartier Stanislas", 54000, 61750, 48694);
        // adresse des agences
        Adresse agenceParis = new Adresse("France", "Paris", "Champs Élysée", "5", "Champs Élysée", 75002, 23350, 48867);
        Adresse agenceMontpellier = new Adresse("France", "Montpellier", "Place de la Comédie", "1", "Centre-ville", 34000, 38760, 43611);

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

        // Creation et ajout des agences
        agences.add(new Agence("#74AgParis", "Agence Paris",agenceParis));
        agences.add(new Agence("#58AgMontpellier", "Agence Montpellier",agenceMontpellier));
        agences.get(0).setLogin(new Login("agparis@gmail.com", "agparis"));
        agences.get(1).setLogin(new Login("agmonte@gmail.com", "agmonte"));

        // Ajouter des hôtels à la liste
        hotels.add(new Hotel("Hôtel de Paris", 5, chambreList, adresseParis, agences));
        hotels.add(new Hotel("Hôtel de Montpellier", 4, chambreList, adresseMontpellier, agences));
        hotels.add(new Hotel("Nîmes", 5, chambreList, adresseNimes, agences));
        hotels.add(new Hotel("Perpignan", 4, chambreList, adressePerpignan, agences));
        hotels.add(new Hotel("Nancy", 4, chambreList, adresseNancy, agences));
        hotels.add(new Hotel("Hôtel de Montpellier 1 ", 4, chambreList, adresseMontpellier1, agences));
        for (Agence agence : agences) {
            agence.setHotels(hotels);
        }

        for (Hotel hotel : agences.get(0).getHotels()) {
            for (Chambre chambre : hotel.getChambres()) {
                chambre.setPrix(chambre.calculerPrix(2, chambre));
            }
        }

        for (Hotel hotel : agences.get(1).getHotels()) {
            for (Chambre chambre : hotel.getChambres()) {
                chambre.setPrix(chambre.calculerPrix(5, chambre));
            }
        }


        reservationList.add(new Reservation(new Client("SALEM", "Ahmedou", "ah@gmail.com"), agences.get(1), hotels.get(5), chambreList.get(2), LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)));
    }
    @Override
    public List<Offre> consulterLesOffres(String identifiant, String password, String ville,
                                          XMLGregorianCalendar dateDebutReservation,
                                          XMLGregorianCalendar dateFinReservation,
                                          Integer nbPersonne) throws LoginException, VilleNotFoundException, ChambreNotFoundException {
        Login login = new Login(identifiant, password);
        Agence agencePartenaire = checkLogin(login);  // Vérifier le login de l'agence partenaire
        boolean villeTrouvee = false;  // Flag pour vérifier si on trouve des hôtels dans la ville

        // Parcourir les hôtels de l'agence partenaire
        for (Hotel hotel : agencePartenaire.getHotels()) {
            if (hotel.getAdresse().getVille().equalsIgnoreCase(ville)) {
                villeTrouvee = true;

                // Vérifier les chambres disponibles dans cet hôtel
                boolean chambreDisponible = false;
                for (Chambre ch : hotel.getChambres()) {
                    if (ch.getNbLit() == nbPersonne && isChambreDisponible(ch, hotel, dateDebutReservation, dateFinReservation)) {
                        offresDisponibles.add(new Offre(ch, dateDebutReservation, ch.getPrix()));
                        chambreDisponible = true;
                    }
                }

                // Si aucune chambre disponible, lever une exception
                if (!chambreDisponible) {
                    throw new ChambreNotFoundException("Aucune chambre disponible pour " + nbPersonne + " personnes dans cet hôtel.");
                }
            }
        }

        // Si aucune ville n'a été trouvée dans les hôtels de l'agence, lever une exception
        if (!villeTrouvee) {
            throw new VilleNotFoundException("Aucune offre disponible dans la ville de " + ville);
        }

       for(Offre offre : offresDisponibles) {
           System.out.println(offre);
        }

        return offresDisponibles;
    }

    private boolean isChambreDisponible(Chambre chambre, Hotel hotel, XMLGregorianCalendar dateDebut, XMLGregorianCalendar dateFin) {
        // Vous pouvez ajouter ici votre logique de vérification de la disponibilité des chambres (dates)
        return chambre.checkDisponible(hotel.getNom(), reservationList,
                new DateUtils().convertToLocalDate(dateDebut),
                new DateUtils().convertToLocalDate(dateFin));
    }


    @Override
    public int calculNbHotel() {
        return hotels.size();
    }

    @Override
    public List<Agence> getAgences() {
        return agences;
    }

    @Override
    public List<Hotel> getHotels() {
        return hotels;
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationList;
    }

    @Override
    public List<Chambre> getHotelChambres(Hotel hotel) {
        return hotel.getChambres();
    }


    public Agence checkLogin(Login login) throws LoginException {
        Agence agencePartenaire = null;
        for(Agence ag : agences) {
            if (ag.getLogin().getIdentifiant().equalsIgnoreCase(login.getIdentifiant())) {
                if (!ag.validerAgenceLogin(login)) {
                    throw new LoginException("Identifiant ou mot de passe incorrect");
                }
                agencePartenaire = ag;
            }
        }
        return agencePartenaire;
    }
}
