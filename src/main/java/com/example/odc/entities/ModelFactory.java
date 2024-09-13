package com.example.odc.entities;

import com.example.odc.database.DatabaseFactory;
import com.example.odc.entities.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ModelFactory {

    private static Map<Class<?>, Object> instances = new HashMap<>();

//    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> myClassName) {
        if (!instances.containsKey(myClassName)) {
            synchronized (ModelFactory.class) {
                if (!instances.containsKey(myClassName)) {
                    try {
                        instances.put(myClassName, myClassName.getDeclaredConstructor().newInstance(DatabaseFactory.createDatabase("List")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return (T) instances.get(myClassName);
    }

    public static Article createArticle() {
        return getInstance(Article.class);
    }

    public static ArticleDette createArticleDette() {
        return getInstance(ArticleDette.class);
    }

    public static Client createClient() {
        return getInstance(Client.class);
    }

    public static Dette createDette() {
        return getInstance(Dette.class);
    }

    public static Paiement createPaiement() {
        return getInstance(Paiement.class);
    }

    public static User createUser() {
        return getInstance(User.class);
    }
}
