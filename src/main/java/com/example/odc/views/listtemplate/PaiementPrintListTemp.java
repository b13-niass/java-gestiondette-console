package com.example.odc.views.listtemplate;

import com.example.odc.entities.impl.Client;
import com.example.odc.entities.impl.Paiement;

import java.util.List;

public class PaiementPrintListTemp {

    // Méthode pour afficher les paiements dans un tableau
    public static void printTable(List<Paiement> paiements) {
        if (paiements.isEmpty()) {
            System.out.println("La liste des paiements est vide.");
            return;
        }

        // Afficher l'entête du tableau
        System.out.printf("%-5s %-15s %-15s %-10s\n",
                "ID", "Client", "ID Dette", "Montant");
        System.out.println("-----------------------------------------------");

        // Parcourir chaque paiement et afficher les détails
        for (Paiement paiement : paiements) {
            Client client = paiement.getDette().getClient();
            System.out.printf("%-5d %-15s %-15d %-10.2f\n",
                    paiement.getId(),
                    client.getSurnom(),
                    paiement.getDette().getId(),
                    paiement.getMontant()
            );
        }
    }
}
