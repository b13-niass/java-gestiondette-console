package com.example.odc.views.listtemplate;

import com.example.odc.entities.Article;
import com.example.odc.entities.ArticleDette;
import com.example.odc.entities.Client;
import com.example.odc.entities.Dette;

import java.util.Collection;
import java.util.List;


public class DettePrintListTemp {
    public static void printTable(Collection<Dette> dettes) {
//        // Afficher l'entête du tableau
//        System.out.printf("%-5s %-20s %-10s %-15s %-15s %-20s %-15s %-15s\n",
//                "ID Dette", "Client", "Montant Dette", "Téléphone", "Adresse",
//                "Article", "Prix Unitaire", "Quantité Vendue");
//        System.out.println("----------------------------------------------------------------------------------------------------------------------");
//
//        for (Dette dette : dettes) {
//            Client client = dette.getClient();
//            List<ArticleDette> articleDettes = dette.getArticlesDette(dette.getId());
//
//            boolean firstArticle = true;
//
//            for (ArticleDette articleDette : articleDettes) {
//                Article article = articleDette.getArticle();
//
//                if (firstArticle) {
//                    // Afficher la dette et les informations du client une seule fois pour le premier article
//                    System.out.printf("%-5d %-20s %-10.2f %-15s %-15s ",
//                            dette.getId(),
//                            client.getSurnom(),
//                            dette.getMontant(),
//                            client.getTelephone(),
//                            client.getAdresse()
//                    );
//                    firstArticle = false;
//                } else {
//                    // Pour les lignes suivantes, ne pas répéter les informations sur la dette et le client
//                    System.out.print(" ".repeat(65)); // Ajustez la largeur en fonction des colonnes à sauter
//                }
//
//                // Afficher les détails de l'article
//                System.out.printf("%-20s %-15.2f %-15d\n",
//                        article.getLibelle(),
//                        article.getPrixUnitaire(),
//                        articleDette.getQteVente()
//                );
//            }
//        }
    }
}
