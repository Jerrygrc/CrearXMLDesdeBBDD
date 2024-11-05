package com.utad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String pwd = "postgres";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Conexión establecida");

            String queryCreate = "CREATE TABLE IF NOT EXISTS regalos (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nombre VARCHAR(50), " +
                    "regalo VARCHAR(50), " +
                    "euros DOUBLE PRECISION);";
            Statement stmt = conn.createStatement();
            stmt.execute(queryCreate);

            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
