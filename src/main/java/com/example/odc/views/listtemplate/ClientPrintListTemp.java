package com.example.odc.views.listtemplate;
import com.example.odc.entities.Client;
import com.example.odc.entities.User;

import java.lang.reflect.Field;
import java.util.List;
public class ClientPrintListTemp {

    public static void printTable(List<Client> clients) {
        if (clients.isEmpty()) {
            System.out.println("La liste des clients est vide.");
            return;
        }

        // Les champs de base du client (sans l'objet User)
        Field[] clientFields = Client.class.getDeclaredFields();
        Field[] userFields = User.class.getDeclaredFields();

        // Calculer la largeur des colonnes, on ignore la colonne `User`
        int[] columnWidths = new int[clientFields.length - 1 + userFields.length];

        // Déterminer la largeur des champs client, en ignorant le champ `user`
        int columnIndex = 0;
        for (int i = 0; i < clientFields.length; i++) {
            if (!clientFields[i].getName().equals("user")) {
                columnWidths[columnIndex] = clientFields[i].getName().length();
                columnIndex++;
            }
        }

        // Déterminer la largeur des champs user (s'ils existent)
        for (int i = 0; i < userFields.length; i++) {
            columnWidths[clientFields.length - 1 + i] = userFields[i].getName().length();
        }

        // Ajuster la largeur des colonnes en fonction des données
        for (Client client : clients) {
            columnIndex = 0;
            for (int i = 0; i < clientFields.length; i++) {
                if (!clientFields[i].getName().equals("user")) {
                    clientFields[i].setAccessible(true);
                    try {
                        String value = String.valueOf(clientFields[i].get(client));
                        if (value.length() > columnWidths[columnIndex]) {
                            columnWidths[columnIndex] = value.length();
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    columnIndex++;
                }
            }

            // Si le client a un user, ajuster aussi les colonnes du user
            if (client.getUser() != null) {
                User user = client.getUser();
                for (int i = 0; i < userFields.length; i++) {
                    userFields[i].setAccessible(true);
                    try {
                        String value = String.valueOf(userFields[i].get(user));
                        if (value.length() > columnWidths[clientFields.length - 1 + i]) {
                            columnWidths[clientFields.length - 1 + i] = value.length();
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Afficher l'entête des colonnes (Client sans User + User)
        StringBuilder header = new StringBuilder();
        columnIndex = 0;
        for (int i = 0; i < clientFields.length; i++) {
            if (!clientFields[i].getName().equals("user")) {
                header.append(String.format("%-" + columnWidths[columnIndex] + "s | ", clientFields[i].getName()));
                columnIndex++;
            }
        }
        for (int i = 0; i < userFields.length; i++) {
            header.append(String.format("%-" + columnWidths[clientFields.length - 1 + i] + "s | ", userFields[i].getName()));
        }
        System.out.println(header.toString());

        // Séparateur
        StringBuilder separator = new StringBuilder();
        for (int width : columnWidths) {
            separator.append("-".repeat(width + 2)).append("|"); // Ajuster la taille pour inclure le séparateur
        }
        System.out.println(separator.toString());

        // Afficher les données des clients et leurs utilisateurs (si présents)
        for (Client client : clients) {
            StringBuilder row = new StringBuilder();

            // Données du client (sans la colonne user)
            columnIndex = 0;
            for (int i = 0; i < clientFields.length; i++) {
                if (!clientFields[i].getName().equals("user")) {
                    clientFields[i].setAccessible(true);
                    try {
                        String value = String.valueOf(clientFields[i].get(client));
                        row.append(String.format("%-" + columnWidths[columnIndex] + "s | ", value));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    columnIndex++;
                }
            }

            // Données de l'utilisateur associé
            if (client.getUser() != null) {
                User user = client.getUser();
                for (int i = 0; i < userFields.length; i++) {
                    userFields[i].setAccessible(true);
                    try {
                        String value = String.valueOf(userFields[i].get(user));
                        row.append(String.format("%-" + columnWidths[clientFields.length - 1 + i] + "s | ", value));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Si pas d'utilisateur, on laisse des cellules vides pour les colonnes utilisateur
                for (int i = 0; i < userFields.length; i++) {
                    row.append(String.format("%-" + columnWidths[clientFields.length - 1 + i] + "s | ", ""));
                }
            }

            System.out.println(row.toString());
        }
    }

}
