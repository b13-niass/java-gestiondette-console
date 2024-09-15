package com.example.odc.views;

import com.example.odc.entities.ModelFactory;
import com.example.odc.entities.builders.ClientBuilder;
import com.example.odc.entities.builders.UserBuilder;
import com.example.odc.entities.impl.Client;
import com.example.odc.entities.impl.Dette;
import com.example.odc.entities.impl.Paiement;
import com.example.odc.entities.impl.User;
import com.example.odc.enums.Role;
import com.example.odc.services.BoutiquierService;
import com.example.odc.services.ServiceFactory;
import com.example.odc.services.UserService;
import com.example.odc.validators.InputValidator;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.ansi;

import java.util.List;
import java.util.Scanner;

public class BoutiquierView {

    private Scanner scanner;
    private boolean isLoggedIn;
    private BoutiquierService boutiquierService;
    private UserService userService;

    public BoutiquierView() {
        scanner = new Scanner(System.in);
        isLoggedIn = true;
        this.boutiquierService = ServiceFactory.createService(BoutiquierService.class);
        this.userService = ServiceFactory.createService(UserService.class);
    }

    public void displayMenu() {
        while (isLoggedIn) {
            clearConsole();
            System.out.println("\n************** Menu Boutiquier **************");
            System.out.println("1- Ajouter un Client");
            System.out.println("2- Lister les Clients");
            System.out.println("3- Créer un compte user pour un Client");
            System.out.println("4- Créer une dette pour un Client");
            System.out.println("5- Lister les dettes d'un Client");
            System.out.println("6- Lister les Paiements d'une dette");
            System.out.println("7- Logout");
            System.out.println("********************************************");

            int choice = promptChoice();
            handleMenuChoice(choice);
        }
    }

    private int promptChoice() {
        System.out.print("\nVeuillez entrer votre choix: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Choix invalide. Entrez un numéro.");
            scanner.next(); // clear the invalid input
        }
        return scanner.nextInt();
    }

    private void handleMenuChoice(int choice) {
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                addClient();
                break;
            case 2:
                listClients();
                break;
            case 3:
                createUserAccountForClient();
                break;
            case 4:
                createDebtForClient();
                break;
            case 5:
                listClientDebts();
                break;
            case 6:
                listDebtPayments();
                break;
            case 7:
                logout();
                break;
            default:
                System.out.println("Choix non valide. Veuillez réessayer.");
        }
    }

    private void addClient() {
        clearConsole();
        String surnom = "";
        String telephone = "";
        System.out.println("Ajouter un Client:");
        boolean phoneExist = true;
        boolean surnomExist = true;
        while (surnomExist) {
             surnom = InputValidator.getValidName("surnom");
            Client user = this.boutiquierService.findBySurnom(surnom);
            if (user != null) {
                System.out.println("Ce surnom existe déjà. Veuillez entrer un autre surnom.");
            }else {
                surnomExist = false;
            }
        }
        while (phoneExist) {
             telephone = InputValidator.getValidPhoneNumber();
            Client user = this.boutiquierService.findByTelephone(telephone);
            if (user != null){
                System.out.println("Ce numéro de téléphone existe déjà. Veuillez entrer un autre numéro.");
            }else {
                phoneExist = false;
            }
        }

        String adresse = InputValidator.getValidName("adresse");
        Client client = (new ClientBuilder())
                .setSurnom(surnom)
                .setTelephone(telephone)
                .setAdresse(adresse).build();
        boutiquierService.create(client);

        // Implémentation pour ajouter le client
        System.out.println("Client '" + surnom +" - "+ telephone +" - "+adresse + "' ajouté avec succès.");
        promptReturnToMenu();
    }

    private void listClients() {
        clearConsole();
        System.out.println("Liste des Clients:");
        List<Client> clients = ModelFactory.createClient().all();
        PrintListView.printTable(clients);
        promptReturnToMenu();
    }

    private void createUserAccountForClient() {
        clearConsole();
        boolean loginExist = true;
        boolean clientExist = false;
        int idClient = 0;
        String login = "";
        System.out.println("Créer un compte utilisateur pour un Client:");

        while (!clientExist){
             idClient = InputValidator.getValidInteger("Entrer l'identifiant du client");

            Client client = this.boutiquierService.find(idClient);
            if (client == null) {
                System.out.println("Client non trouvé. Veuillez entrer un id valide.");
            } else {
                if (client.getUser() != null) {
                    System.out.println("Le client possède déjà un compte utilisateur.");
                }else {
                    clientExist = true;
                }
            }
        }

        String nom = InputValidator.getValidName("nom");
        String prenom = InputValidator.getValidName("prénom");
        while (loginExist) {
             login = InputValidator.getValidName("login");
            User user = this.userService.findUserByLogin(login);
            if (user!= null) {
                System.out.println("Ce login existe déjà. Veuillez entrer un autre login.");
            } else {
                loginExist = false;
            }
        }
        String password = InputValidator.getValidPassword();

        User user = (new UserBuilder())
                .setLogin(login)
                .setNom(nom)
                .setPrenom(prenom)
                .setPassword(password)
                .setRole(Role.CLIENT).build();
        Client client = (new ClientBuilder()).setId(idClient).build();

        boutiquierService.createCompte(client, user);

        // Implémentation pour créer un compte user
        System.out.println("Compte utilisateur créé pour le client '" + prenom+" - "+ nom + "'.");
        promptReturnToMenu();
    }

    private void createDebtForClient() {
        clearConsole();
        System.out.println("Créer une dette pour un Client:");
        String clientName = prompt("Nom du Client: ");
        String debtAmount = prompt("Montant de la dette: ");
        // Implémentation pour créer une dette
        System.out.println("Dette de " + debtAmount + " créée pour le client '" + clientName + "'.");
        promptReturnToMenu();
    }

    private void listClientDebts() {
        clearConsole();
        boolean clientExist = false;
        int idClient = 0;
        System.out.println("Lister les dettes d'un Client:");
        while (!clientExist){
            idClient = InputValidator.getValidInteger("Entrer l'identifiant du client");

            Client client = this.boutiquierService.find(idClient);
            if (client == null) {
                System.out.println("Client non trouvé. Veuillez entrer un id valide.");
            } else {
                    clientExist = true;
            }
        }
        Client client = (new ClientBuilder()).setId(idClient).build();
        List<Dette> debts = boutiquierService.getDette(client);
        PrintListView.printTable(debts);
        promptReturnToMenu();
    }

    private void listDebtPayments() {
        clearConsole();
        System.out.println("Lister les paiements d'une dette:");
        boolean detteExist = false;
        int idDette = 0;
        Dette dette = null;
        while (!detteExist){
            idDette = InputValidator.getValidInteger("Entrer l'identifiant de la dette");

             dette = this.boutiquierService.findDetteById(idDette);
            if (dette == null) {
                System.out.println("Dette non trouvé. Veuillez entrer un id valide.");
            } else {
                detteExist = true;
            }
        }

        List<Paiement> payments = boutiquierService.getPaiement(dette);
        PrintListView.printTable(payments);
        // Implémentation pour lister les paiements d'une dette
        promptReturnToMenu();
    }

    private void logout() {
        System.out.println("Déconnexion réussie.");
        isLoggedIn = false;
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private void promptReturnToMenu() {
        System.out.println("\nAppuyez sur Entrée pour revenir au menu...");
        scanner.nextLine();
    }

    public static void clearConsole() {
        // Activer Jansi pour permettre l'usage des séquences ANSI
        AnsiConsole.systemInstall();

        // Effacer la console et replacer le curseur en haut à gauche
        System.out.print(ansi().eraseScreen().cursor(0, 0));

        // Désactiver Jansi
        AnsiConsole.systemUninstall();
    }
}
