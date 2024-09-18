package com.example.odc.services;

import com.example.odc.entities.User;

public interface UserService extends IService<User> {
    public User findByLogin(String login);
}
