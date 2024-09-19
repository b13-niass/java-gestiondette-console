package com.example.odc.entities;

import com.example.odc.database.Database;
import com.example.odc.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Génère getters, setters, toString, equals, et hashCode
@Builder
@AllArgsConstructor  // Génère le constructeur avec tous les arguments
@NoArgsConstructor   // Génère le constructeur par défaut
public class User{
    private int id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private Role role;
}