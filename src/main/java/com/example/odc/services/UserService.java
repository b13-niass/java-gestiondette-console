package com.example.odc.services;

import com.example.odc.entities.impl.User;

public interface UserService {
    public boolean authenticate(String login, String password);
    public User findUserByLogin(String login);
}
