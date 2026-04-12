package persistence;

import entities.Topping;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToppingMapper {

    public static List<Topping> getAllToppings(ConnectionPool connectionPool) {

        List<Topping> toppingList = new ArrayList<>();

        String sql = "SELECT * FROM public.toppings";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("topping_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                toppingList.add(new Topping(id, name, price));
            }
        } catch(SQLException e) {
            throw new DatabaseException("DB fejl: " + e.getMessage());
        }
        return toppingList;
    }

    public static Topping getToppingById(int id, ConnectionPool connectionPool) {

        Topping t = null;

        String sql = "SELECT * FROM public.toppings WHERE topping_id = ?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    t = new Topping(id, name, price);
                }
            }
        } catch(SQLException e) {
            throw new DatabaseException("DB fejl: " + e.getMessage());
        }
        return t;
    }
}