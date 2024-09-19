package com.example.odc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Génère getters, setters, toString, equals, et hashCode
@Builder
@AllArgsConstructor  // Génère le constructeur avec tous les arguments
@NoArgsConstructor   // Génère le constructeur par défaut
public class ArticleDette{
    private int id;
    private double prixVente;
    private int qteVente;
    private Dette dette;
    private Article article;

}