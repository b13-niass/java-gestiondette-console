package com.example.odc;

import com.example.odc.entities.ModelFactory;
import com.example.odc.entities.impl.JsonDataModel;
import com.example.odc.entities.impl.User;
import com.example.odc.views.LoginView;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
//        User user = ModelFactory.createUser();
//
//        System.out.println(user.all().get(0));
        LoginView loginView = new LoginView();
        loginView.displayLoginScreen();
    }
}
