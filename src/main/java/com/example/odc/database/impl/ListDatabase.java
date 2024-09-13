package com.example.odc.database.impl;

import com.example.odc.database.IDatabase;
import com.example.odc.entities.impl.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListDatabase implements IDatabase {

    public List<Article> articles = null;
    public List<Dette> dettes = null;
    public List<Client> clients = null;
    public List<Paiement> paiements = null;
    public List<User> users = null;
    public List<ArticleDette> articleDettes = null;
    public JsonDataModel listData;

    private static final String DATA_FILE_PATH = "/home/no-one/Desktop/Sonatel Academy/Java/projects/java-gestiondette-console/data.json";

    public ListDatabase() {
//        this.articles = new ArrayList<>();
//        this.dettes = new ArrayList<>();
//        this.clients = new ArrayList<>();
//        this.paiements = new ArrayList<>();
//        this.users = new ArrayList<>();
//        this.articleDettes = new ArrayList<>();
//
//        this.connect();
    }

    @Override
    public JsonDataModel connect() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.listData = objectMapper.readValue(new File(DATA_FILE_PATH), JsonDataModel.class);
//
//            this.articles.addAll(this.listData.getArticles());
//            this.clients.addAll(this.listData.getClients());
//            this.dettes.addAll(this.listData.getDettes());
//            this.paiements.addAll(this.listData.getPaiements());
//            this.users.addAll(this.listData.getUsers());
//            this.articleDettes.addAll(this.listData.getArticlesDette());
            return this.listData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}