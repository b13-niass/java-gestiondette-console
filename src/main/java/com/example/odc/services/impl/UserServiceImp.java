package com.example.odc.services.impl;

import com.example.odc.entities.User;
import com.example.odc.repositories.UserRepository;
import com.example.odc.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository repository;

    public UserServiceImp(@Qualifier("collection") UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<User> all() {
        return List.of();
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public int save(User entity) {
        return 0;
    }

    @Override
    public int delete(User entity) {
        return 0;
    }

    @Override
    public int update(int id, User entity) {
        return 0;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }
}
