package com.example.odc.database;

import java.sql.Connection;
import java.sql.ResultSet;

public interface Database {
    public Connection getConnection();
    public boolean closeConnection();
    public ResultSet executeQuery(String query);
    public int executeUpdate(String query);
    public int executePreparedUpdate(String sql, Object... params);
    public ResultSet executePreparedQuery(String sql, Object... params);
}