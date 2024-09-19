package com.example.odc.services.impl;

import com.example.odc.entities.User;
import com.example.odc.repositories.UserIRepository;
import com.example.odc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Component
public class UserServiceImp implements UserService {
    private final UserIRepository repository;

    @Autowired
    public UserServiceImp(@Qualifier("userRepoImplCollection") UserIRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<User> all() {
        return this.repository.findAll();
    }

    @Override
    public User find(int id) {
        return this.repository.find(id);
    }

    @Override
    public int save(User entity) {
        return this.repository.save(entity);
    }

    @Override
    public int delete(int id) {
        return this.repository.delete(id);
    }

    @Override
    public int update(int id, User entity) {
        return this.repository.update(id, entity);
    }

    @Override
    public User findByLogin(String login) {
        return this.repository.findByLogin(login);
    }
    @Override
    public boolean authenticate(String username, String password) {
        return this.repository.authenticate(username, password);
    }
}
