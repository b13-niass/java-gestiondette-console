package com.example.odc.validators;

import java.util.Scanner;

public class InputValidator {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getValidEmail() {
        String email;
        do {
            System.out.print("Veuillez entrer un email valide : ");
            email = scanner.nextLine();
        } while (!CommonValidator.isValidEmail(email));
        return email;
    }

    public static String getValidName(String field) {
        String name;
        do {
            System.out.printf("Veuillez entrer un %s valide (non vide et sans caractères spéciaux) : ", field);
            name = CommonValidator.filterField(scanner.nextLine());
        } while (CommonValidator.isEmptyField(name));
        return name;
    }

    public static double getValidAmount(String message) {
        double amount;
        while (true) {
            System.out.print(message);
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount >= 0) {
                    break;
                } else {
                    System.out.println("Le montant ne peut pas être négatif.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Montant invalide. Veuillez entrer un nombre.");
            }
        }
        return amount;
    }

    public static String getValidPhoneNumber() {
        String phoneNumber;
        do {
            System.out.print("Veuillez entrer un numéro de téléphone valide (format 761234567, 771234567, ou 781234567) : ");
            phoneNumber = scanner.nextLine();
        } while (!CommonValidator.isValidPhoneNumber(phoneNumber));
        return phoneNumber;
    }

    public static String getValidPassword() {
        String password;
        do {
            System.out.print("Veuillez entrer un mot de passe valide : ");
            password = scanner.nextLine();
        } while (!CommonValidator.isValidPassword(password));
        return password;
    }

    public static int getValidInteger(String message) {
        String input;
        while (true) {
            System.out.print(message+" : ");
            input = scanner.nextLine();
            try {
                return CommonValidator.parseInteger(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un entier.");
            }
        }
    }
}
