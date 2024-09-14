package com.example.odc.database;

import com.example.odc.database.impl.ListDatabase;

public class DatabaseFactory {
    private static IDatabase databaseInstance; // Singleton instance

    public static IDatabase createDatabase(String type) {
        if (databaseInstance == null) {
            if ("List".equalsIgnoreCase(type)) {
                databaseInstance = new ListDatabase();
            }else if ("JDBC".equalsIgnoreCase(type)){
                // Implementation JDBC database
            } else {
                throw new IllegalArgumentException("Type de base de données non supporté : " + type);
            }
        }

        return databaseInstance;
    }
}