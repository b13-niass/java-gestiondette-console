package com.example.odc.services;

import com.example.odc.entities.Article;

public interface ArticleService extends IService<Article> {
    public Article findByLibelle(String libelle);
}
