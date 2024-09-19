package com.example.odc.entities;

import com.example.odc.database.Database;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Génère getters, setters, toString, equals, et hashCode
@Builder
@AllArgsConstructor  // Génère le constructeur avec tous les arguments
@NoArgsConstructor   // Génère le constructeur par défaut
public class Article {
    private int id;
    private String libelle;
    private double prixUnitaire;
    private int quantiteEnStock;

}