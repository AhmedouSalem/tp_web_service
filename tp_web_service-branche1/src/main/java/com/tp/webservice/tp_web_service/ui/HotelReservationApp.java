

package com.tp.webservice.tp_web_service.ui;

import com.tp.webservice.tp_web_service.model.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class HotelReservationApp extends Application {

    private List<Hotel> hotels = new ArrayList<>(); // Liste pour stocker les hôtels disponibles

    @Override
    public void start(Stage primaryStage) {

        // Créer la liste des réservations
        ArrayList<Reservation> reservations = new ArrayList<>();

        primaryStage.setTitle("Réservation d'Hôtel");

        // Création de la grille pour la mise en page
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Champs de saisie
        TextField villeField = new TextField();
        villeField.setPromptText("Ville de séjour");
        grid.add(new Label("Ville :"), 0, 0);
        grid.add(villeField, 1, 0);

        DatePicker dateArriveeField = new DatePicker();
        grid.add(new Label("Date d'arrivée :"), 0, 1);
        grid.add(dateArriveeField, 1, 1);

        DatePicker dateDepartField = new DatePicker();
        grid.add(new Label("Date de départ :"), 0, 2);
        grid.add(dateDepartField, 1, 2);

        TextField prixMinField = new TextField();
        prixMinField.setPromptText("Prix minimum");
        grid.add(new Label("Prix minimum :"), 0, 3);
        grid.add(prixMinField, 1, 3);

        TextField prixMaxField = new TextField();
        prixMaxField.setPromptText("Prix maximum");
        grid.add(new Label("Prix maximum :"), 0, 4);
        grid.add(prixMaxField, 1, 4);

        TextField etoilesField = new TextField();
        etoilesField.setPromptText("Nombre d'étoiles");
        grid.add(new Label("Étoiles :"), 0, 5);
        grid.add(etoilesField, 1, 5);

        TextField nombrePersonnesField = new TextField();
        nombrePersonnesField.setPromptText("Nombre de personnes");
        grid.add(new Label("Nombre de personnes :"), 0, 6);
        grid.add(nombrePersonnesField, 1, 6);

        Button rechercheButton = new Button("Rechercher Hôtels");
        grid.add(rechercheButton, 0, 7);

        Button clearFieldButton = new Button("Vider les champs");
        grid.add(clearFieldButton, 1, 7);


        // Liste pour afficher les résultats
        ListView<String> hotelListView = new ListView<>();
        grid.add(hotelListView, 0, 8, 2, 1);

        clearFieldButton.setOnAction(event -> {
            // Effacer les champs de saisie
            villeField.clear();
            dateArriveeField.setValue(null);
            dateDepartField.setValue(null);
            prixMinField.clear();
            prixMaxField.clear();
            etoilesField.clear();
            nombrePersonnesField.clear();

            // Vider la liste des résultats
            hotelListView.getItems().clear();
        });


        // ces exemples sont pris sur internet pour le test
        Adresse adresseParis = new Adresse("France", "Paris", "Avenue de l'Opéra", "5", "Quartier de l'Opéra", 75002, 23350, 48867);
        Adresse adresseMontpellier = new Adresse("France", "Montpellier", "Place de la Comédie", "1", "Centre-ville", 34000, 38760, 43611);
        Adresse adresseMontpellier1 = new Adresse("France", "Montpellier", "Faubourg Boutonnet", "119", "Boutonnet", 34000, 38760, 43611);
        Adresse adresseNimes = new Adresse("France", "Nîmes", "Rue de la République", "3", "Centre-ville", 30000, 43600, 43836);
        Adresse adressePerpignan = new Adresse("France", "Perpignan", "Rue de l'Ange", "2", "Centre historique", 66000, 28950, 42688);
        Adresse adresseNancy = new Adresse("France", "Nancy", "Rue Stanislas", "54", "Quartier Stanislas", 54000, 61750, 48694);


        // Création des chambres
        List<Chambre> chambreList = new ArrayList<>();
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
        reservations.add(new Reservation(new Client(), hotels.get(5), chambreList.get(2), LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)));
        rechercheButton.setOnAction(e -> {
            // rechercher des hôtels
            String ville = villeField.getText();
            String prixMinStr = prixMinField.getText();
            String prixMaxStr = prixMaxField.getText();
            String etoilesStr = etoilesField.getText();
            String nombrePersonnesStr = nombrePersonnesField.getText();
            LocalDate dateArrivee = dateArriveeField.getValue();
            LocalDate dateDepart = dateDepartField.getValue();

            // Recherche fictive
            hotelListView.getItems().clear();

            if (ville.isEmpty() || etoilesStr.isEmpty() || prixMinStr.isEmpty() || prixMaxStr.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.");
                alert.showAndWait();
                return;
            }

            if (!reservations.isEmpty()) {
                for (Hotel hotel : hotels) {
                    if (hotel.getAdresse().getVille().equalsIgnoreCase(ville) &&
                            hotel.getNbEtoile() == Integer.parseInt(etoilesStr)) {
                        for (Chambre chambre : hotel.getChambres()) {
                            if (chambre.checkDisponible(hotel.getNom(), reservations, dateArrivee, dateDepart, Double.parseDouble(prixMinStr), Double.parseDouble(prixMaxStr), Integer.parseInt(nombrePersonnesStr))) {
                                hotelListView.getItems().add(hotel.getNom());
                                break; // Évitez d'ajouter plusieurs fois le même hôtel
                            }
                        }
                    }
                }
            } else {
                for (Hotel hotel : hotels) {
                    if (hotel.getAdresse().getVille().equalsIgnoreCase(ville) &&
                            hotel.getNbEtoile() == Integer.parseInt(etoilesStr)) {
                        for (Chambre chambre : hotel.getChambres()) {
                            //if (chambre.checkDisponible(reservations, dateArrivee, dateDepart, Double.parseDouble(prixMinStr), Double.parseDouble(prixMaxStr))) {
                            hotelListView.getItems().add(hotel.getNom());
                            break; // Évitez d'ajouter plusieurs fois le même hôtel
                            //}
                        }
                    }
                }

            }
            // Ajoutez un log si aucun hôtel n'est trouvé
            if (hotelListView.getItems().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Aucun hôtel trouvé avec les critères spécifiés.");
                alert.showAndWait();
            }
        });


        // Action sur la sélection d'un hôtel
        hotelListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Trouver l'hôtel correspondant au nom sélectionné
                Hotel selectedHotel = findHotelByName(newValue);
                if (selectedHotel != null) {
                    afficherDetailsHotel(selectedHotel, reservations, dateArriveeField.getValue(), dateDepartField.getValue(), prixMinField.getText(), prixMaxField.getText(), Integer.parseInt(nombrePersonnesField.getText()));
                }
            }
        });

        Scene scene = new Scene(grid, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void afficherDetailsHotel(Hotel hotel, List<Reservation> reservationList, LocalDate dateArrivee, LocalDate dateDepart, String prixMin, String prixMax, Integer nbLit) {
        // Créer une nouvelle fenêtre pour afficher les détails
        Stage detailStage = new Stage();
        detailStage.setTitle("Détails de l'Hôtel");

        // Créer une grille pour les détails
        GridPane detailGrid = new GridPane();
        detailGrid.setPadding(new Insets(10));
        detailGrid.setVgap(8);
        detailGrid.setHgap(10);

        // Ajouter les détails de l'hôtel
        detailGrid.add(new Label("Nom :"), 0, 0);
        detailGrid.add(new Label(hotel.getNom()), 1, 0);

        detailGrid.add(new Label("Adresse :"), 0, 1);
        detailGrid.add(new Label(hotel.getAdresse().getRue() + ", " + hotel.getAdresse().getVille() + ", " + hotel.getAdresse().getPays()), 1, 1);

        detailGrid.add(new Label("Étoiles :"), 0, 2);
        detailGrid.add(new Label(String.valueOf(hotel.getNbEtoile())), 1, 2);

        // Afficher les chambres disponibles
        detailGrid.add(new Label("Chambres disponibles :"), 0, 3);
        ListView<Chambre> chambreListView = new ListView<>();
        detailGrid.add(chambreListView, 0, 4, 2, 1);

        double minPrice = Double.parseDouble(prixMin); // Convertir les prix en double
        double maxPrice = Double.parseDouble(prixMax);

        // Vérifier les chambres disponibles selon les critères
        for (Chambre chambre : hotel.getChambres()) {
            if (chambre.checkDisponible(hotel.getNom(), reservationList, dateArrivee, dateDepart, minPrice, maxPrice, nbLit)) {
                // Ajouter la chambre à la ListView
                chambreListView.getItems().add(chambre);
            }
        }

        // Ajouter un bouton pour confirmer la réservation
        Button reserverButton = new Button("Réserver");
        detailGrid.add(reserverButton, 1, 5);

        // Gérer la sélection de chambre
        chambreListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Activer le bouton de réservation seulement si une chambre est sélectionnée
                reserverButton.setDisable(false);
            }
        });

        // Action pour le bouton de réservation
        reserverButton.setOnAction(e -> {
            Chambre selectedChambre = chambreListView.getSelectionModel().getSelectedItem();
            if (selectedChambre != null) {
                afficherFormulaireReservation(reservationList, selectedChambre, hotel, dateArrivee, dateDepart, detailStage);
            }
        });

        // Configurer la scène et afficher la nouvelle fenêtre
        Scene detailScene = new Scene(detailGrid, 400, 300);
        detailStage.setScene(detailScene);
        detailStage.show();
    }

    private void afficherFormulaireReservation(List<Reservation>reservations, Chambre chambre , Hotel hotel, LocalDate dateArrival, LocalDate dateFinSejour, Stage detailStage) {
        Stage reservationStage = new Stage();
        reservationStage.setTitle("Réservation de Chambre");

        GridPane reservationGrid = new GridPane();
        reservationGrid.setPadding(new Insets(10));
        reservationGrid.setVgap(8);
        reservationGrid.setHgap(10);

        // Champs de saisie pour les informations du client
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        reservationGrid.add(new Label("Nom :"), 0, 0);
        reservationGrid.add(nomField, 1, 0);

        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");
        reservationGrid.add(new Label("Prénom :"), 0, 1);
        reservationGrid.add(prenomField, 1, 1);

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        reservationGrid.add(new Label("Email :"), 0, 2);
        reservationGrid.add(emailField, 1, 2);

        // Champs pour la carte de crédit
        TextField numeroCarteField = new TextField();
        numeroCarteField.setPromptText("Numéro de carte");
        reservationGrid.add(new Label("Numéro de carte :"), 0, 3);
        reservationGrid.add(numeroCarteField, 1, 3);

        TextField typeCarteField = new TextField();
        typeCarteField.setPromptText("Type de carte (Visa, MasterCard, etc.)");
        reservationGrid.add(new Label("Type de carte :"), 0, 4);
        reservationGrid.add(typeCarteField, 1, 4);

        // Ajout d'un bouton pour confirmer la réservation
        Button confirmerButton = new Button("Confirmer Réservation");
        reservationGrid.add(confirmerButton, 1, 5);

        confirmerButton.setOnAction(e -> {
            // Vérifier si les champs est vide
            if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || emailField.getText().isEmpty() || numeroCarteField.getText().isEmpty() || typeCarteField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.");
                alert.showAndWait();
                return;
            }
            // Créer un client
            Client client = new Client(nomField.getText(), prenomField.getText(), emailField.getText());

            // Créer et ajouter la carte de crédit
            int numeroCarte = Integer.parseInt(numeroCarteField.getText());
            String typeCarte = typeCarteField.getText();
            CarteCredit carteCredit = new CarteCredit(numeroCarte, typeCarte);
            client.getCarteCredits().add(carteCredit); // Ajouter à la liste de cartes du client
            // Créer une réservation
            Reservation reservation = new Reservation(client, hotel, chambre, dateArrival, dateFinSejour);
            client.getReservations().add(reservation);
            // Ajouter la réservation à la liste
            reservations.add(reservation); //

            // Simuler le paiement
            String paiementResultat = carteCredit.payerMontant(chambre.getPrix());
            System.out.println(paiementResultat);

            // Fermer la fenêtre de réservation
            reservationStage.close();

            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Réservation confirmée !");
            alert.setOnHidden(event -> {
                // Fermer également la fenêtre de détails si elle est ouverte
                detailStage.close(); // Fermez la fenêtre de détails ici, si vous avez une référence à detailStage
            });
            alert.showAndWait();
        });

        Scene reservationScene = new Scene(reservationGrid, 300, 200);
        reservationStage.setScene(reservationScene);
        reservationStage.show();
    }





    private Hotel findHotelByName(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getNom().equals(hotelName)) {
                return hotel;
            }
        }
        return null; // Retourne null si aucun hôtel ne correspond
    }


    public static void main(String[] args) {
        launch(args);
    }
}
