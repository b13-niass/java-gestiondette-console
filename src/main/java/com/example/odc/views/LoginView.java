package com.example.odc.views;

import java.io.IOException;
import java.util.Scanner;

public class LoginView {

    private Scanner scanner;
    private boolean loggedIn;

    public LoginView() {
        scanner = new Scanner(System.in);
    }

    public void displayLoginScreen() {
        System.out.println("\n************************************");
        System.out.println("          Bienvenue dans l'App      ");
        System.out.println("************************************");
        System.out.println("         Veuillez vous connecter      ");
        System.out.println("************************************");
        while (!loggedIn) {
            String username = prompt("Nom d'utilisateur : ");
            String password = prompt("Mot de passe : ");

            handleLogin(username, password);
        }
        System.out.println("\nConnexion réussie !");
    }

    public String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public void handleLogin(String username, String password) {
        // Simulate credential validation
        // Replace this with actual validation logic
        this.loggedIn = validateCredentials(username, password);
        if (!this.loggedIn) {
            System.out.println("\nNom d'utilisateur ou mot de passe incorrect.");
        }
    }

    public boolean validateCredentials(String username, String password) {
        // Example validation: replace with actual credential check
        return "admin".equals(username) && "password".equals(password);
    }

    public static void clearConsole() {
//        System.out.print("\033c");
//        try {
//        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

}
