package com.example.odc.database.impl;

import com.example.odc.database.IDatabase;
import com.example.odc.entities.impl.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ListDatabase implements IDatabase {

    public JsonDataModel listData;

    private static final String DATA_FILE_PATH = "/home/no-one/Desktop/Sonatel_Academy/Java/projects/java-gestiondette-console/data.json";

    public ListDatabase() {
//        System.out.println(this.connect());
    }

    @Override
    public JsonDataModel connect() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            this.listData = objectMapper.readValue(new File(DATA_FILE_PATH), JsonDataModel.class);
            return this.listData;
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier JSON : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}