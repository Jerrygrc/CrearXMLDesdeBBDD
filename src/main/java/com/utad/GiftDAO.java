package com.utad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GiftDAO {

        public void insertGift(Gift gift) {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO regalos (nombre, regalo, euros) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO regalos (nombre, regalo, euros) VALUES (?, ?, ?)");
            stmt.setString(1, gift.getNombre());
            stmt.setString(2, gift.getRegalo());
            stmt.setDouble(3, gift.getPrecio());
            stmt.executeUpdate();
            System.out.println("Regalo para " + gift.getNombre() + "añadido (" + gift.getRegalo() + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
