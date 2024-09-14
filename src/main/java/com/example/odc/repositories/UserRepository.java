package com.example.odc.repositories;

import com.example.odc.entities.impl.User;

public interface UserRepository {
    public boolean authenticate(String login, String password);
    public User findUserByLogin(String login);
}
