package persistence;

import entities.ListOfOrders;
import entities.User;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper {

    public static int makeOrder(int user_id, ConnectionPool connectionPool) {

        int orderId = 0;

        String sql = "INSERT INTO orders (user_id) VALUES (?) RETURNING order_id;";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                orderId = rs.getInt("order_id");
            }



        } catch (SQLException e) {
            throw new DatabaseException("DB fejl: " + e.getMessage());
        }
        return orderId;

    }

    public static void addItemToOrder(int orderId, int topId, int botId, ConnectionPool connectionPool) {

        String sql = "INSERT INTO order_items (order_id, bottom_id, topping_id) VALUES (?,?,?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, botId);
            ps.setInt(3, topId);

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected != 1) {
                throw new DatabaseException("Fejl ved indsættelse af items til ordre");
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl: " + e.getMessage());
        }

    }



    public static ListOfOrders listOfOrdersByUser(User user, ConnectionPool connectionPool){

        ListOfOrders newListOfOrders = null;
        int user_id = user.getId();

        String sql = "SELECT * FROM public.orders WHERE user_id = ?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, user_id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                }
            }
        }catch(SQLException e) {
            throw new DatabaseException("DB fejl: " + e.getMessage());
        }
        return newListOfOrders;
    }
}
