package com.example.odc.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {
     Connection getConnection();
     boolean closeConnection();
     int executePreparedUpdate(String sql, Object... params);
     <T> T executePreparedQuery(String sql, Class<T> entityClass, Object... params) throws SQLException;
}