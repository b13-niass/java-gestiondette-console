package com.example.odc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Génère getters, setters, toString, equals, et hashCode
@AllArgsConstructor  // Génère le constructeur avec tous les arguments
@NoArgsConstructor   // Génère le constructeur par défaut
public class Client{
    private int id;
    private String surnom;
    private String telephone;
    private String adresse;
    private User user = null;
}