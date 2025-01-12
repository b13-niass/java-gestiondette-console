package com.example.odc.services;

import com.example.odc.entities.Article;
import org.springframework.stereotype.Component;

@Component
public interface ArticleService extends IService<Article> {
    public Article findByLibelle(String libelle);
}
