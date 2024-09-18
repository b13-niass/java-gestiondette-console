//package com.example.odc.views;
//
//import java.util.Scanner;
//
//public class ClientView {
//
//    private Scanner scanner;
//    private boolean isLoggedIn;
//
//    public ClientView() {
//        scanner = new Scanner(System.in);
//        isLoggedIn = true;
//    }
//
//    public void displayMenu() {
//        while (isLoggedIn) {
//            clearConsole();
//            System.out.println("\n************** Menu Client **************");
//            System.out.println("1- Payer une dette");
//            System.out.println("2- Lister mes dettes");
//            System.out.println("3- Lister les paiements d'une dette");
//            System.out.println("4- Logout");
//            System.out.println("****************************************");
//
//            int choice = promptChoice();
//            handleMenuChoice(choice);
//        }
//    }
//
//    private int promptChoice() {
//        System.out.print("\nVeuillez entrer votre choix: ");
//        while (!scanner.hasNextInt()) {
//            System.out.println("Choix invalide. Entrez un numéro.");
//            scanner.next(); // clear invalid input
//        }
//        return scanner.nextInt();
//    }
//
//    private void handleMenuChoice(int choice) {
//        scanner.nextLine(); // Consume newline
//        switch (choice) {
//            case 1:
//                payDebt();
//                break;
//            case 2:
//                listDebts();
//                break;
//            case 3:
//                listDebtPayments();
//                break;
//            case 4:
//                logout();
//                break;
//            default:
//                System.out.println("Choix non valide. Veuillez réessayer.");
//        }
//    }
//
//    private void payDebt() {
//        clearConsole();
//        System.out.println("Payer une dette:");
//        String debtId = prompt("ID de la dette: ");
//        String amount = prompt("Montant à payer: ");
//        // Implémentation pour payer une dette
//        System.out.println("Le paiement de " + amount + " pour la dette " + debtId + " a été effectué.");
//        promptReturnToMenu();
//    }
//
//    private void listDebts() {
//        clearConsole();
//        System.out.println("Liste de vos dettes:");
//        // Implémentation pour lister les dettes
//        System.out.println("Dette 1\nDette 2\nDette 3");
//        promptReturnToMenu();
//    }
//
//    private void listDebtPayments() {
//        clearConsole();
//        System.out.println("Lister les paiements d'une dette:");
//        String debtId = prompt("ID de la dette: ");
//        // Implémentation pour lister les paiements d'une dette
//        System.out.println("Paiement 1\nPaiement 2\nPaiement 3");
//        promptReturnToMenu();
//    }
//
//    private void logout() {
//        System.out.println("Déconnexion réussie.");
//        isLoggedIn = false;
//    }
//
//    private String prompt(String message) {
//        System.out.print(message);
//        return scanner.nextLine().trim();
//    }
//
//    private void promptReturnToMenu() {
//        System.out.println("\nAppuyez sur Entrée pour revenir au menu...");
//        scanner.nextLine();
//    }
//
//    private void clearConsole() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//    }
//}
