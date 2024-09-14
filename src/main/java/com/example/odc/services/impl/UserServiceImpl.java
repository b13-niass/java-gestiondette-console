package com.example.odc.services.impl;

import com.example.odc.entities.impl.User;
import com.example.odc.repositories.UserRepository;

public class UserServiceImpl implements UserRepository {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return false;
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }
}
