package com.example.odc.services.impl;

import com.example.odc.entities.impl.User;
import com.example.odc.repositories.UserRepository;
import com.example.odc.services.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository repository;
//    int[] a = new int[3];
//    Coll
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return repository.authenticate(login, password);
    }

    @Override
    public User findUserByLogin(String login) {
        return repository.findUserByLogin(login);
    }
}
