package com.example.odc.services.impl;

import com.example.odc.entities.Article;
import com.example.odc.repositories.ArticleRepository;
import com.example.odc.services.ArticleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {
    private final ArticleRepository repository;
    public ArticleServiceImp(@Qualifier("collection") ArticleRepository repository){
        this.repository = repository;
    }

    @Override
    public Collection<Article> all() {
        return List.of();
    }

    @Override
    public Article find(int id) {
        return null;
    }

    @Override
    public int save(Article entity) {
        return 0;
    }

    @Override
    public int delete(Article entity) {
        return 0;
    }

    @Override
    public int update(int id, Article entity) {
        return 0;
    }

    @Override
    public Article findByLibelle(String libelle) {
        return null;
    }
}
