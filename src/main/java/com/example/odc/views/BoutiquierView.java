package com.example.odc.views;

import java.util.Scanner;

public class BoutiquierView {

    private Scanner scanner;
    private boolean isLoggedIn;

    public BoutiquierView() {
        scanner = new Scanner(System.in);
        isLoggedIn = true;
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
        System.out.println("Ajouter un Client:");
        String clientName = prompt("Nom du Client: ");
        // Implémentation pour ajouter le client
        System.out.println("Client '" + clientName + "' ajouté avec succès.");
        promptReturnToMenu();
    }

    private void listClients() {
        clearConsole();
        System.out.println("Liste des Clients:");
        // Implémentation pour lister les clients
        System.out.println("Client 1\nClient 2\nClient 3");
        promptReturnToMenu();
    }

    private void createUserAccountForClient() {
        clearConsole();
        System.out.println("Créer un compte utilisateur pour un Client:");
        String clientName = prompt("Nom du Client: ");
        // Implémentation pour créer un compte user
        System.out.println("Compte utilisateur créé pour le client '" + clientName + "'.");
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
        System.out.println("Lister les dettes d'un Client:");
        String clientName = prompt("Nom du Client: ");
        // Implémentation pour lister les dettes d'un client
        System.out.println("Dette 1\nDette 2\nDette 3");
        promptReturnToMenu();
    }

    private void listDebtPayments() {
        clearConsole();
        System.out.println("Lister les paiements d'une dette:");
        String debtId = prompt("ID de la dette: ");
        // Implémentation pour lister les paiements d'une dette
        System.out.println("Paiement 1\nPaiement 2\nPaiement 3");
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

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
