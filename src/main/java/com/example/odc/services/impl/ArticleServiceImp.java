package com.example.odc.services.impl;

import com.example.odc.entities.Article;
import com.example.odc.repositories.ArticleIRepository;
import com.example.odc.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Component
public class ArticleServiceImp implements ArticleService {
    private final ArticleIRepository repository;

    @Autowired
    public ArticleServiceImp(@Qualifier("articleRepoImplJDBC") ArticleIRepository repository){
        this.repository = repository;
    }

    @Override
    public Collection<Article> all() {
        return this.repository.findAll();
    }

    @Override
    public Article find(int id) {
        return this.repository.find(id);
    }

    @Override
    public int save(Article entity) {
        return this.repository.save(entity);
    }

    @Override
    public int delete(int id) {
        return this.repository.delete(id);
    }

    @Override
    public int update(int id, Article entity) {
        return this.repository.update(id, entity);
    }

    @Override
    public Article findByLibelle(String libelle) {
        return this.findByLibelle(libelle);
    }
}
