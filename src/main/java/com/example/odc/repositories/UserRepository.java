package com.example.odc.repositories;

import com.example.odc.entities.User;

public interface UserRepository extends Repository<User>{
    public User findByLogin(String login);
}
