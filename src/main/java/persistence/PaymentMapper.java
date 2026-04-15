package persistence;

import entities.User;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentMapper {

    public static void pay(double priceForCostumer, User costumer, ConnectionPool connectionPool) {

        String userEmail = costumer.getEmail();
        int userId = costumer.getId();
        double negativePriceForCostumer = -priceForCostumer;

        String sql = "UPDATE public.users SET balance = balance + ? WHERE user_id = ? AND email = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDouble(1, negativePriceForCostumer);
            ps.setInt(2, userId);
            ps.setString(3, userEmail);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved at betale for Ordren");
            } else {
                throw new DatabaseException("Fejl i login. Prøv igen");
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl: " + e.getMessage());
        }
    }
}

