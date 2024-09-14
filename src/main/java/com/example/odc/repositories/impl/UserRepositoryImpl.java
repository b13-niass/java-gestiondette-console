package com.example.odc.repositories.impl;

import com.example.odc.entities.ModelFactory;
import com.example.odc.entities.impl.User;
import com.example.odc.repositories.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean authenticate(String login, String password) {
        List<User> users = ModelFactory.createUser().all();
        User user = users.stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {
        List<User> user = ModelFactory.createUser().all();
        return user.stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
    }
}
