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
        double balanceBefore = costumer.getBalance();
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
            }
            double newBalance = balanceBefore-negativePriceForCostumer;
            //sætter costumer balance i Java også, ikke kun i db
            costumer.setBalance(newBalance);

        } catch (SQLException e) {
            throw new DatabaseException("DB fejl: " + e.getMessage());
        }
    }
}

